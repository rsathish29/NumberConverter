FROM openjdk:8-jdk

EXPOSE 8080
RUN mkdir -p /opt/app
WORKDIR /opt/app

ARG JAR_PATH
ARG APM_AGENT_JAR_PATH

COPY $JAR_PATH /opt/app
COPY $APM_AGENT_JAR_PATH /opt/app

# This commands starts the spring boot application along with the apm agent that collects APM Metrics
CMD java -javaagent:/opt/app/elastic-apm-agent.jar $JVM_OPTIONS -jar $JAR_NAME