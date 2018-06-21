#!/usr/bin/env bash

# Execute in script dir
cd $(dirname ${0})

JAEGER_SERVICE_NAME=jaeger
JAEGER_IMAGE_VERSION=latest
JAEGER_ZIPKIN_SERVICE_NAME=zipkin

RSYSLOG_SERVICE_NAME=rsyslog
RSYSLOG_GIT_URL=https://github.com/b0x1/microservice-architecture
RSYSLOG_GIT_REF=master
RSYSLOG_GIT_CONTEXT_DIR=openshift/rsyslog


function createJaeger() {
    oc new-app -f templates/jaeger.yml  \
      -p "APP_NAME=${JAEGER_SERVICE_NAME}" \
      -p "SERVICE_NAME=${JAEGER_SERVICE_NAME}" \
      -p "IMAGE_VERSION=${JAEGER_IMAGE_VERSION}" \
      -p "ZIPKIN_SERVICE_NAME=${JAEGER_ZIPKIN_SERVICE_NAME}"
}

function createSyslogServer() {
    oc new-app -f templates/rsyslog.yml \
      -p "APP_NAME=${RSYSLOG_SERVICE_NAME}" \
      -p "SERVICE_NAME=${RSYSLOG_SERVICE_NAME}" \
      -p "GIT_URL=${RSYSLOG_GIT_URL}" \
      -p "GIT_REF=${RSYSLOG_GIT_REF}" \
      -p "GIT_CONTEXT_DIR=${RSYSLOG_GIT_CONTEXT_DIR}"
}

function create() {
    createJaeger
    createSyslogServer
}

function deleteJaeger() {
    oc delete all -l app=${JAEGER_SERVICE_NAME}
}

function deleteSyslogServer() {
    oc delete all -l app=${RSYSLOG_SERVICE_NAME}
}

function delete() {
    deleteJaeger
    deleteSyslogServer
}

$1