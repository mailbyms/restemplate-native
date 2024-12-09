# restemplate-native
spring native 使用restemplate请求。使用 jackson 把请求内容序列化为 json，并把请求结果反序列化为类

## 注意事项
- 在类 `CustomHints` registerType() 需要反序列化的实体类，都
- 在 spring boot 主应用类里加上注解 `@ImportRuntimeHints(CustomHints.class)`
- 在要反序列化实体类的业务类（如 `TransApiService`）加上注解 `@RegisterReflectionForBinding(GptResultDto.class)`
- `pom.xml` 里要加上依赖（如果使用 `spring-boot-starter-web` 则已自带） 
  ```
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.15.2</version>
    </dependency>
  ```
- 把类序列化为 json 时，注意 META-INF/native-image/reflect-config.json 里的配置，以及 @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) 的注解
## 编译
```shell
  mvn clean && mvn -DskipTests -Pnative native:compile
```
环境：
- Oracle GraalVM 21+35.1
- Apache Maven 3.8.8