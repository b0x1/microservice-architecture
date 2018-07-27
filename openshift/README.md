# Openshift Enviroment with OpenTracing and Syslog Server

## Usage

First, install [the oc client tools](https://www.openshift.org/download.html) and 
[oc-cluster-wrapper](https://github.com/openshift-evangelists/oc-cluster-wrapper)

Then run `setup.sh create`. This will bootstrap a local Openshift enviroment with Jaeger as a OpenTracing agent 
and a simple Syslog server (currently not optimally configured, but working).

ATTENTION: The vanilla example app will not start properly if no syslog server is running in the OpenShift stage.

### Currently running on

- oc v3.5.5.31.24
- kubernetes v1.5.2+43a9be4
- docker 1.13.1 (API version 1.26)