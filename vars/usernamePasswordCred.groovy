/*
    * Configures single (username & password) credentials for a folder in global domain
    *  if already exists a credentials with defined username - it will update it
    *  if more than one exists - the first one it encounters will be updated
*/

import java.util.logging.Logger;
import jenkins.model.*;
import com.cloudbees.hudson.plugins.folder.*;
import com.cloudbees.hudson.plugins.folder.properties.*;
import com.cloudbees.hudson.plugins.folder.properties.FolderCredentialsProvider.FolderCredentialsProperty;
import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;
    

@NonCPS
def call(String folderName, String user_name, String user_pass, String description) {
    if(description.isEmpty()) {
        description = "No Description"
    }

    // Init
    def logger = Logger.getLogger("")
    jenkins = Jenkins.instance

    // String user_name = "my_user_name"
    // String user_pass = "my_new_pass"
    // String description = "my desc"
    // String folderName = "folder123"

    String id = java.util.UUID.randomUUID().toString()
    Credentials c = new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, id, "description: "+id, user_name, user_pass)

    logger.info("Configuring domain credentials for folder: $folderName")
    for (folder in jenkins.getAllItems(Folder.class)) {
        if(folder.name.equals(folderName)) {
            AbstractFolder<?> folderAbs = AbstractFolder.class.cast(folder)
            FolderCredentialsProperty property = folderAbs.getProperties().get(FolderCredentialsProperty.class)

            // If not defined FolderCredentialsProperty yet - define and finish
            if(property == null) {
                logger.info("Initializing folder credentials store and add credentials in global store")
                property = new FolderCredentialsProperty([c])
                folderAbs.addProperty(property)
                jenkins.save()
                return
            }

            // Check for existing credentials - and update their password & description
            //   will update the first credentials it encounters
            def credentials_store = property.getStore()
            List<com.cloudbees.plugins.credentials.Credentials> folderCredentialsList = property.getCredentials()
            for (creds in folderCredentialsList) {
                logger.info("Checking existing credentials of folder: $folderName for user: $user_name")
                if (creds.username.equals(user_name)) {
                    // Found username - updating it
                    //  Try to update the creds of the folder:
                    def updateResult = credentials_store.updateCredentials(
                            com.cloudbees.plugins.credentials.domains.Domain.global(),
                            creds,
                            new UsernamePasswordCredentialsImpl(creds.scope, creds.id, description, creds.username, user_pass)
                    )
                    if (updateResult) {
                        println "Update successful"
                    } else {
                        println "Update failed"
                    }
                    jenkins.save()
                    return
                }

            }

            logger.info("Didn't find credntials with username: $user_name - adding new one")

            // If got here - then:
            //  1. There is already a FolderCredentials property defined for folder: folderName
            //  2. didn't find any credentials(of username & password type) with username == user_name
            // so just add the new credentials
            property.getStore().addCredentials(Domain.global(), c)
            jenkins.save()
            return
        }
    }
}

