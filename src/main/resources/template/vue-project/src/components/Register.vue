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
        <div>
            <span>
            Username:
        </span>
        <input class="inputUsername">
        </div>
        <div>
            <span>
            Password:
        </span>
        <input class="inputPassword">
        </div>
        <div>
            <span>
            Firstname:
        </span>
        <input class="inputFirstName">
        </div>
        <div>
            <span>
            Lastname:
        </span>
        <input class="inputLastName">
        </div>
        <div>
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

</style>