<script>

export default{
    methods: {
        reloadPage(){
            window.location.reload();
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
        <input class="inputUsername">
        </div>
        <div class="Input-Container">
            <span>
            Password:
        </span>
        <input class="inputPassword">
        </div>
        <div class="Input-Container">
            <span>
            Firstname:
        </span>
        <input class="inputFirstName">
        </div>
        <div class="Input-Container">
            <span>
            Lastname:
        </span>
        <input class="inputLastName">
        </div>
        <div class="Input-Container">
        <span>
            personnel Number:
        </span>
        <input class="inputPersonnelNumber">
        </div>
        <div>
            <button class="enterButton" @click="reloadPage">Enter</button>
        </div>
    </div>
</template>
<style>
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