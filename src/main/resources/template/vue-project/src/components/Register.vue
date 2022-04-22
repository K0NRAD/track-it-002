<script>
const registrationMethod = async (username, password, personnelNumber, firstName, lastName) => {
    const registrationErrorMessageDiv = document.getElementById("showErrorMessage");
    const registrationRestEndpoint = `http://localhost:8080/api/employee/registerEmployee?username=${username}&password=${password}&personnelNumber=${personnelNumber}&firstName=${firstName}&lastName=${lastName}`;

    const response = await fetch(
        registrationRestEndpoint,
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

    if(!jsonResponse){
        registrationErrorMessageDiv.style.display="block";
        setTimeout(
            () => {
                registrationErrorMessageDiv.style.display="none";
            },
            5000
        );
    }
}

export default{
    data(){
        return {
        }
    },
    methods: {
        register(){
            const username = document.getElementById("inputUsernameRegistration").value;
            const password = document.getElementById("inputPasswordRegistration").value;
            const personnelNumber = document.getElementById("inputPersonnelNumberRegistration").value;
            const firstName = document.getElementById("inputFirstNameRegistration").value;
            const lastName = document.getElementById("inputLastNameRegistration").value;

            registrationMethod(
                username,
                password,
                personnelNumber,
                firstName,
                lastName
            );
        }
    }
}

</script>
<template>
    <div>
        <span class="LogIn-Text">
            Register
        </span>
        <div class="Input-Container">
            <span>
            Username:
        </span>
        <input class="inputUsername" id="inputUsernameRegistration">
        </div>
        <div class="Input-Container">
            <span>
            Password:
        </span>
        <input class="inputPassword" id="inputPasswordRegistration">
        </div>
        <div class="Input-Container">
            <span>
            Firstname:
        </span>
        <input class="inputFirstName" id="inputFirstNameRegistration">
        </div>
        <div class="Input-Container">
            <span>
            Lastname:
        </span>
        <input class="inputLastName" id="inputLastNameRegistration">
        </div>
        <div class="Input-Container">
        <span>
            personnel Number:
        </span>
        <input class="inputPersonnelNumber" id="inputPersonnelNumberRegistration">
        </div>
        <br>
        <div id="showErrorMessage">
            <p>We couldn't register you. Try again.</p>
        </div>
        <div>
            <button class="enterButton" @click="register">Enter</button>
        </div>
    </div>
</template>
<style>
#showErrorMessage{
    display: none;
}
.enterButton{
        width: 20rem;
        padding: 1.5rem;
        margin: 3rem 1rem 0.5rem 1rem;
        border-radius: 1.5rem;
        border: none;
    }
    .enterButton:hover{
        background-color: darkslategray;
        color: azure;
    }
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
.LogIn-Text{
    font-size: 3rem;
    font-weight: 600;
}
</style>