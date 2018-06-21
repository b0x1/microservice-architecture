FROM debian:jessie

# From: https://github.com/helderco/docker-rsyslog

RUN apt-get update && \
    apt-get install rsyslog --no-install-recommends -y && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY rsyslog.conf /etc/

RUN chown -R 1001:0 /var/run/ /var/log/ /etc/rsyslog.d/ \
 && chmod -R ug=rwx,o= /var/run/ /var/log/ /etc/rsyslog.d/

EXPOSE 10514/tcp 10514/udp

ENTRYPOINT rsyslogd && tail -f /var/log/syslog