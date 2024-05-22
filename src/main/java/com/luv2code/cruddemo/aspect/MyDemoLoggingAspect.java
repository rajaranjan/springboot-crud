package com.luv2code.cruddemo.aspect;

import com.luv2code.cruddemo.entity.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
// @Order(1) order annotation gives the precedence and ordering in execution
public class MyDemoLoggingAspect {

    // pointcut declaration
//    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
//    private void forDaoPackage(){};
//
//    @Before("forDaoPackage()")
    // let's start with a @Before advice
    @Before("execution(public void com.luv2code.cruddemo.dao.AccountDao.addAccount())")
    // @Before("execution(* add*(com.luv2code.cruddemo.entity.Account))") Passing the account object
    // @Before("execution(* add*(..))") matches any number of parameters
    // @Before("execution(* public void com.luv2code.cruddemo.dao.*.*(..))") matches any class, any method and any params
    public void beforeAddAccountAdvice() {

        System.out.println("\n====> Executing @Before advice on addAccount()");
    }

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    private void getterPointCut(){};

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    private void setterPointCut(){};

    // create a pointcut: include package ... exclude setter or getter
    @Pointcut("forDaoPackage() && !(getterPointCut() || setterPointCut())")
    private void forDaoPackageNoGetterSetter(){};

    @AfterReturning(
            pointcut = "execution(* com.luv2code.cruddemo.dao.InstructorDao.findStudents(..))",
            returning = "result"
    )
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Student> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====> Executing @AfterReturning on method: " + method);

        // print out the result of the method call
        System.out.println("\n=====> result is: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.cruddemo.dao.AccountDao.getAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====> Executing @AfterReturning on method: " + method);

        // log the exception
        System.out.println("\n=====> Exception is: " + theExc);
    }
    @After("execution(* com.luv2code.cruddemo.dao.AccountDao.getAccounts(..))")
    public void afterFinallyAccountAdvice(JoinPoint theJoinPoint) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====> Executing @After (finally) on method: " + method);

    }

    @Around("execution(* com.luv2code.cruddemo.dao.StudentDao.getStudents(..))")
    public void aroundStudentReturn(JoinPoint theJoinPoint) {

        System.out.println("\n ====> Executing @Around on method");
    }

}
