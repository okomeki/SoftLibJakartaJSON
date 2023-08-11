# SoftLibJakartaJSON

SoftLibJSON の Jakarta EE対応でJDK 9以降が必要になるため分割したものです。

SoftLibRebind経由で相互に変換が可能です。

## 機能

- Jakarta EE JSON Processing
- Jakarta EE JSON Binding
- SoftLibRebind での変換 

## Maven

pom.xml のdependency は次のような感じで追加します。
SoftLibJSONなどから依存関係になっているので使いたい機能を個別に含めればSoftLibRebindも含まれます。
```
<dependency>
  <groupId>net.siisise<groupId>
  <artifactId>softlib-jakarta.json</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <type>jar</type>
</dependency>
```
まだSNAPSHOTです。

## LICENSE

 Apache 2.0
 okomeki または しいしせねっと

