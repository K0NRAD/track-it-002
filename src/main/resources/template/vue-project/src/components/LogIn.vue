<script>


</script>
<template>
    <div>
        <span class="LogIn-Text">
            LogIn
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
        <div class="placeHolder">

        </div>
        <div>
            <button class="enterButton" @click="$router.push('trackIt')">Enter</button>
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