package org.zerock.sp1.mapper;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.sp1.domain.Sauth;
import org.zerock.sp1.domain.Smember;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SmemberMapperTest {
  
  @Autowired
  private SmemberMapper smemberMapper;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Test
  public void testInsert(){
    for (int i = 0; i < 100; i++) {
      Smember smember = Smember.builder().mid("user"+i)
              .mpw(passwordEncoder.encode("1111"))
              .name("user"+i)
              .nickname("사용자" +i)
              .build();
      
      smemberMapper.register(smember);
  
      if(i >= 80){
        Sauth sauth = Sauth.builder().mid("user"+i).rolename("ADMIN").build();
        smemberMapper.addAuth(sauth);
      }
        Sauth sauth = Sauth.builder().mid("user"+i).rolename("MEMBER").build();
        smemberMapper.addAuth(sauth);
      
    
    }
  }
  
  @Test
  public void testSelectOne(){
    String user = "user90";
    Smember smember = smemberMapper.selectOne(user);
    log.info("=============================");
    log.info(smember);
  }
}
