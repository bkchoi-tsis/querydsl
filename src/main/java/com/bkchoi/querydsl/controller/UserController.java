package com.bkchoi.querydsl.controller;

import com.bkchoi.querydsl.dao.TeamRepository;
import com.bkchoi.querydsl.dao.UserCustomRepository;
import com.bkchoi.querydsl.dao.UserRepository;
import com.bkchoi.querydsl.domain.Teams;
import com.bkchoi.querydsl.domain.Users;
import com.bkchoi.querydsl.dto.respons.TeamUsers;
import com.bkchoi.querydsl.global.common.Common;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserController {

    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;

    private final TeamRepository teamRepository;
    private final Common common;

    @PostMapping("/hello")
    public String createUsers(){

        String teamCode = this.common.getRandomString();

        Teams teams = Teams.builder()
                .name("TeamName"+this.common.getRandomString())
                .code(teamCode)
                .ownerCode(this.common.getRandomString())
                .ownerName("owner"+this.common.getRandomString())
                .upperCode(this.common.getRandomString())
                .build();

        Users users = Users.builder()
                .name("name"+this.common.getRandomString())
                .email("email"+this.common.getRandomString())
                .status(true)
                .phone(new Random().nextInt())
                .teamCode(teamCode)
                .msg("{props:{ACTIVE:\"Y\",COL43:\"N\",COL44:\"N\",COL45:\"N\",COL46:\"Y\",COL47:\"N\",COL48:\"N\",COL49:\"Y\",COL50:\"N\",COL51:\"N\",COL52:\"N\",COL53:\"N\",COL54:\"N\",COL55:\"N\",COL56:\"N\",CRNKEY:\"KRW\",CTRYCODE:\"KR\",CUSTOM17:\"P23516546 \",CUSTOM18:\"\",CUSTOM21:\"1000\",CUSTOM6:\"L\",CUSTOM7:\"015\",EMAILADDRESS:\"yeopp@taekwang.co.kr\",EMPID:\"23516546\",EXPENSEAPPROVER:\"Y\",EXPENSEAPPROVEREMPLOYEEID:\"\",EXPENSEUSER:\"Y\",FIRSTNAME:\"류상엽\",INVOICEAPPROVER:\"Y\",INVOICEUSER:\"Y\",LASTNAME:\"대리\",LEDGERKEY:\"1000\",LOCALENAME:\"ko_KR\",LOGINID:\"yeopp@taekwang.co.kr\",ORGUNIT1:\"THPCLNT100\",ORGUNIT2:\"1000\",ORGUNIT3:\"TK10\",ORGUNIT4:\"CC\",ORGUNIT5:\"131030    \",PASSWORD:\"welcome\",PAYMENTREQUEST:\"Y\",REQUESTAPPROVER:\"Y\",REQUESTAPPROVEREMPLOYEEID:\"\",REQUESTUSER:\"Y\"},StackTrace:\"nets.im.provision.engine.AdaptorTimeoutException: [전자전표 (SAP Concur)] '류상엽(23516546)' 사용자 작업(user.updateUser)이 실행시간(30,000 ms)을 초과하여 중지했습니다.\\n\\tat nets.im.provision.engine.ProvisionTask.throwTimeoutException(ProvisionTask.java:84)\\n\\tat nets.af.system.worker.TaskTimeoutRunner.execute(TaskTimeoutRunner.java:34)\\n\\tat nets.im.provision.engine.AdaptorRunner.execute(AdaptorRunner.java:30)\\n\\tat nets.im.provision.engine.AdaptorHelper.executeOper(AdaptorHelper.java:1019)\\n\\tat nets.im.provision.engine.AdaptorHelper.executeOpers(AdaptorHelper.java:964)\\n\\tat nets.im.provision.engine.AdaptorHelper.executeTarget(AdaptorHelper.java:931)\\n\\tat nets.im.provision.provider.IMAdaptor.executeTarget(IMAdaptor.java:258)\\n\\tat nets.im.provision.provider.IMAdaptor.executeLocal(IMAdaptor.java:226)\\n\\tat nets.im.provision.provider.IMAdaptor.executeLocal(IMAdaptor.java:202)\\n\\tat nets.im.provision.provider.IMAdaptor.executeLocal(IMAdaptor.java:189)\\n\\tat nets.im.provision.queue.app.AdaptorMessageHandler.run(AdaptorMessageHandler.java:24)\\n\\tat nets.im.provision.queue.app.MessageHandler.handleMessage(MessageHandler.java:24)\\n\\tat nets.im.provision.queue.app.ProvisionChannelRunnable.run(ProvisionChannelRunnable.java:36)\\n\\tat nets.im.provision.queue.core.ChannelRunnable.run(ChannelRunnable.java:34)\\n\"}")
                .build();

        this.userRepository.save(users);
        this.teamRepository.save(teams);
        return "ok";
    }

    @GetMapping("/hello")
    public String getUsers(){

        List<Users> allFetchJoin = this.userCustomRepository.findAllFetchJoin();
        for (Users users : allFetchJoin) {
//            System.out.println("users.getUser_id() = " + users.getUser_id());
//            System.out.println("users.getEmail() = " + users.getEmail());
//            System.out.println("users.getName() = " + users.getName());
//            System.out.println("users.getPhone() = " + users.getPhone());
//            System.out.println("users.isStatus() = " + users.isStatus());

            System.out.println("users.getUser_id() = " + users.getId()
                    + " / " + "users.getEmail() = " + users.getEmail()
                    + " / " + "users.getName() = " + users.getName()
                    + " / " + "users.getPhone() = " + users.getPhone()
                    + " / " + "users.isStatus() = " + users.isStatus()
            );
        }

        return "hello";
    }

    @GetMapping("/teamusers")
    public String getTeamUsers(){
        List<TeamUsers> teamUsers = this.userCustomRepository.findTeamUsers();
        for (TeamUsers teamUser : teamUsers) {
            System.out.println("teamUser.getCode() = " + teamUser.getCode());
            System.out.println("teamUser.getOwnerCode() = " + teamUser.getOwnerCode());
            System.out.println("teamUser.toString() = " + teamUser.toString());
        }
        return "ok";

    }
}
