FROM openjdk:8
COPY target/classes/za/ac/nwu/ac/web/ /temp
WORKDIR /tmp
CMD java sb.RestServiceApplication