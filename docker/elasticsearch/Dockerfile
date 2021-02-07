FROM docker.elastic.co/elasticsearch/elasticsearch:6.5.2
COPY elasticsearch.yml /usr/share/elasticsearch/config/
RUN chown -R elasticsearch:elasticsearch /usr/share/elasticsearch/config/
CMD ["elasticsearch", "-Elogger.level=INFO"]