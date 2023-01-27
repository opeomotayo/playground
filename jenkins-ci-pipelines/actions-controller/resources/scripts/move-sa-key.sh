#!/usr/bin/env bash
#
# Generate JWT for Github App
# Pushes the sa-key to a secret place

SECRET_FILE=`python -c "import uuid; print(uuid.uuid1())"`

echo "::add-mask::$RUNNER_TEMP/$SECRET_FILE"
echo "::add-mask::$SECRET_FILE"
mv $SA_KEY_FILE $RUNNER_TEMP/$SECRET_FILE
echo "SA_SECRET_CONSUMER_KEY=$RUNNER_TEMP/$SECRET_FILE" >> $GITHUB_ENV
