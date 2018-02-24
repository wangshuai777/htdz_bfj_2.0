1 spring-cloud-eureka-server注册中心程序
2 application-peer1.properties application-peer2.properties为高可用配置,分别注册向对方即可实现高可用
3 启用高可用脚本
  java -jar htdz_eureka_0.0.1-SNAPSHOT.jar --spirng.profiles.active=peer1
  java -jar htdz_eureka_0.0.1-SNAPSHOT.jar --spirng.profiles.active=peer2
