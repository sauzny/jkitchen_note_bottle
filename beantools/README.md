# bean相关

## 一、bean转换测试

测试类 `com.sauzny.beantools.convert.ConvertTesting`，另有其他说明可查看测试代码中的注释

```
StopWatch '100次': running time (millis) = 27
-----------------------------------------
ms     %     Task name
-----------------------------------------
00000  000%  mapstruct
00027  100%  dozer
00000  000%  beanCopier

StopWatch '10000次': running time (millis) = 233
-----------------------------------------
ms     %     Task name
-----------------------------------------
00004  002%  mapstruct
00227  097%  dozer
00002  001%  beanCopier

StopWatch '1000000次': running time (millis) = 6430
-----------------------------------------
ms     %     Task name
-----------------------------------------
00030  000%  mapstruct
06393  099%  dozer
00007  000%  beanCopier
```