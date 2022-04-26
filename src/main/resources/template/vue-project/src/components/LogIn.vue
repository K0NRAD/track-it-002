<script>

import TestData from "../testdata.json"

var employee = TestData[0][1]

var username = employee.username;
var password = employee.password;
console.log(username)

const loginMethod = async (usernameInput, passwordInput)=>{
   const loginRestEndpoint = `http://localhost:8080/api/employee/loginEmployee?username=${usernameInput}&password=${passwordInput}`;
    
    const response = await fetch(

        loginRestEndpoint,
        {
            method: "GET",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Credentials": "*",
            }
        }
    );

            const jsonResponse = await response.json();

            console.log(response);
            console.log(jsonResponse);
           if (response){
            console.log("Hello World");
             
             
             return true;
            
            }
            else{
                message = "wrong username or password";
                return false;
                
            } 
}


export default{
    data() {
        return {
            TestData: TestData,
            user:{
                name: "",
                password: "",
            },
            message: ""
            
        }
    },
    methods: {
        async LogIn() {
            
            let usernameInput = document.getElementById("inputUsername").value;   
            let passwordInput = document.getElementById("inputPassword").value;

            const loginStatus = await loginMethod(usernameInput, passwordInput);
            if (loginStatus){
                this.$router.push('trackIt');
            }
            

           
        /*
            if ( usernameInput == username && passwordInput == password){
            
             this.$router.push('trackIt')
             
            
            }
            else{
                message = "wrong username or password"
            }*/
     //       elseif(usernameInput != username && passwordInput == password){
     //           message = "username don't exist"
     //       }
     //       elseif(usernameInput == username && passwordInput != password){
     //            message = "wrong password"
     //       }
    
                  
        }
    }
}


</script>
<template>
    <div>
        <span class="LogIn-Text">
            LogIn
        </span>
        <br>
        <span>
                {{ message }}
        </span>
        <div class="Input-Container" >
            
            <span>
                Username:
            </span>
            <input class="inputUsername" id="inputUsername" >
        </div>
        <div class="Input-Container">
            <span>
                Password:
            </span>
            <input class="inputPassword" id="inputPassword" >
        </div>
        <div class="placeHolder">

        </div>
        <div>
            <button class="enterButton" @click="LogIn">Enter</button>
        </div>
    </div>
</template>

<style>
.Input-Container{
    font-size: 1.5rem;
    display: flex;
    margin: 0.5rem 10% 0 10%;
}
.Input-Container span{
    width: 15rem;
    float: left;
    text-align: left;
}
.Input-Container input{
    float: right;
    width: 45rem;
}
.placeHolder{
    height: 6.5rem;
}
</style>