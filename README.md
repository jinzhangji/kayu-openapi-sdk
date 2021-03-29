

### 菜鸟SDK建议开发者使用以下环境，可以避免版本带来的问题
```
    JDK: JDK1.8+
```
### Example


``` 
   OpenApiClient openApiClient = new DefaultOpenApiClient.Builder()
                                       .host("https://www.example.com")
                                       .merchantNo("")
                                       .md5key("")
                                       .build();
                                       
    IBaseParam<R> param = ?(业务参数)                                   
    R result = openApiClient.execute(param)
    if(result.isSuccess()){
        System.out.println(JSON.toJSONString(result));
    }else{
        System.out.println(String.format("status:%s - message:%s",result.getStatus(),result.getMessage()));
    }
    
         
``` 

