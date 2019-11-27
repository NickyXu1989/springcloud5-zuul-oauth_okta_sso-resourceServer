#Spring Cloud 模版  
zuul 作为gateway  
oauth 作为sso，提供oauth2 okta认证方式，底层用户验证为rcba，使用mybatis进行数据查询  
service-alpha 作为resourceserver，使用oauth进行权限验证  