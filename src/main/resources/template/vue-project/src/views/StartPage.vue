<script>
import Welcome from "../components/Welcome.vue"
import LogIn from "../components/LogIn.vue"
import Register from "../components/Register.vue"

export default{
    components: {
        Welcome,
        LogIn,
        Register
    },
    data(){
        return{
            showWelcome : true,
            showLogIn: false,
            showRegister: false,
            showButtonLogIn: true,
            showButtonRegister: true,
            back: false
        }
    },
    methods: {
        activateLogIn() {
            this.showWelcome = false,
            this.showLogIn = true,
            this.showRegister = false,
            this.showButtonLogIn = false,
            this.showButtonRegister = false,
            this.back = true
        },
        activateRegister() {
            this.showWelcome = false,
            this.showLogIn = false,
            this.showRegister = true,
            this.showButtonLogIn = false,
            this.showButtonRegister = false,
            this.back = true
        },
        backToDefault() {
            this.showWelcome = true,
            this.showLogIn = false,
            this.showRegister = false,
            this.showButtonLogIn = true,
            this.showButtonRegister = true,
            this.back = false
        }

    }
}

 
</script>
<template>
    <div class="card">
        <div class="class-container">
            <div>
                <div class="change-area">
                    <Welcome v-if="showWelcome" />
                    <LogIn v-if="showLogIn"/>
                    <Register v-if="showRegister" />
                </div>
                <div>
                    <button class="logIn-Button" v-if="showButtonLogIn" @click="activateLogIn">Log-In</button>
                    <button class="register-Button" v-if="showButtonRegister" @click="activateRegister">Register</button>
                    <button class="backButton" v-if="back" @click="backToDefault">Back</button>
                </div>
            </div>
        </div>
    </div>
</template>
<style>

</style>