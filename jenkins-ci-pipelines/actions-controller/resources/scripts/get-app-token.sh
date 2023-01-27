#!/usr/bin/env bash
#
# Generate JWT for Github App
#
# Inspired by implementation by Will Haley at:
# http://willhaley.com/blog/generate-jwt-with-bash/

set -o pipefail
# Shared content to use as template
header='{
    "alg": "RS256",
    "typ": "JWT"
}'
payload_template='{}'

build_payload() {
    jq -c \
        --arg iat_str "$(date +%s)" \
        --arg app_id "${GITHUB_APP_ID}" \
        '
    ($iat_str | tonumber) as $iat
    | .iat = $iat
    | .exp = ($iat + 300)
    | .iss = ($app_id | tonumber)
    ' <<<"${payload_template}" | tr -d '\n'
}

b64enc() { openssl enc -base64 -A | tr '+/' '-_' | tr -d '='; }
json() { jq -c . | LC_CTYPE=C tr -d '\n'; }
rs256_sign() { openssl dgst -binary -sha256 -sign <(printf '%s\n' "$1"); }

sign() {
    local algo payload sig
    algo=${1:-RS256}
    algo=${algo^^}
    payload=$(build_payload) || return
    signed_content="$(json <<<"$header" | b64enc).$(json <<<"$payload" | b64enc)"
    sig=$(printf %s "$signed_content" | rs256_sign "$GITHUB_APP_PRIVATE_KEY" | b64enc)
    printf '%s.%s\n' "${signed_content}" "${sig}"
}

jwt=$(sign)
token=$(curl -s -k -X POST \
    -H "Authorization: Bearer ${jwt}" \
    -H "Accept: application/vnd.github.v3+json" \
    https://api.github.com/app/installations/"${GITHUB_APP_INSTALLATION_ID}"/access_tokens | jq -r .token)
echo "::add-mask::$token"
echo "GITHUB_TOKEN=$token" >> $GITHUB_ENV
