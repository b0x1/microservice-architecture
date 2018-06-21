# Openshift Enviroment with OpenTracing and Syslog Server

## Usage

First, install [the oc client tools](https://www.openshift.org/download.html) and 
[oc-cluster-wrapper](https://github.com/openshift-evangelists/oc-cluster-wrapper)

Then run the `setup.sh` script. This will bootstrap a local Openshift enviroment with Jaeger as a OpenTracing agent 
and a simple Syslog server (currently not optimally configured, but working).