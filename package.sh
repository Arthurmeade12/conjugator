#!/usr/bin/env bash
set -eu
TARGET="${TARGET:=.}"
JARNAME='conjugator.jar'
MAINCLASS='me.arthurmeade12.conjugator.main'
COMPILEPATH='me/arthurmeade12/conjugator'
WD="$(dirname "${0}")"
javac -d "${TARGET}" "${WD}"/**/*.java # Will need to rewrite this if we have code somewhere other than src
jar -c -f "${TARGET}/${JARNAME}" -e "${MAINCLASS}" "${TARGET}/${COMPILEPATH}"
