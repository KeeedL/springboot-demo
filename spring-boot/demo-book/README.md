## Swagger :
- http://localhost:8080/library-book/swagger-ui.html

## H2 console :
- http://localhost:8080/library-book/h2-console

Get DDL from console :
```
SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '$YOUR_TABLE';
SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_NAME = '$YOUR_TABLE';
```
