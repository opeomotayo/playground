SHELL := /bin/bash
KUSTOMIZE_DIRS := $(shell find ../ -type f -maxdepth 2 -name "kustomization.yaml" -exec dirname {} ';' )

.PHONY: validate
validate:  ## run kubectl kustomize to validate yaml files
	@for i in $(KUSTOMIZE_DIRS); do \
		echo "Validating $$i" ; \
		kustomize build $$i > /dev/null || (echo "$$i failed $$?"; exit 1); \
	done

.PHONY: release
release: SCOPE ?= auto
release: .tag-final ## Releases a final versuon of the software artifacts in a given [SCOPE=auto] of `major`, `minor` or `patch`.

.tag-final:
	$(call require,SCOPE)
	semtag final -s '$(SCOPE)'
