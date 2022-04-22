<script>
import DailyTimeTracker from "../components/DailyTimeTracker.vue";
import WeeklyTimeTrackerSheet from "../components/WeeklyTimeTrackerSheet.vue"
import ProtocolForm from "../components/ProtocolForm.vue"; 


export default{
    components: {
        DailyTimeTracker,
        WeeklyTimeTrackerSheet,
        ProtocolForm
    },
    data(){
        return{
            activeDailyTracker : true,
            activeWeeklyTimeSheet : true,
            activeProtocolForm : true,
            activeSidebar: false,
        }
    },
    methods: {
        setActiveDailyTracker() {
            this.activeDailyTracker = true;
            this.activeWeeklyTimeSheet = false;
            this.activeProtocolForm = false;
            this.activeSidebar = true;
            document.getElementById("dailyTracker").classList.remove("card-container");
            document.getElementById("dailyTracker").classList.add("card-containerActive");
            document.getElementById("button-RowCenter").classList.remove("button-RowCenter");
            document.getElementById("button-RowCenter").classList.add("button-RowCenterActive");
            
           
            
        },
        setActiveWeeklyTimeSheet(){
            this.activeDailyTracker = false;
            this.activeWeeklyTimeSheet = true;
            this.activeProtocolForm = false;
            this.activeSidebar = true;
            
            document.getElementById("weeklyTimeSheet").classList.remove("card-container");
            document.getElementById("weeklyTimeSheet").classList.add("card-containerActive");
        },
        setActiveProtocolForm(){
            this.activeDailyTracker = false;
            this.activeWeeklyTimeSheet = false;
            this.activeProtocolForm = true;
            this.activeSidebar = true;
            document.getElementById("protocolForm").classList.remove("card-container");
            document.getElementById("protocolForm").classList.add("card-containerActive");
        },
        showDailyTracker(){
            this.activeDailyTracker = true;
            this.activeWeeklyTimeSheet = false;
            this.activeProtocolForm = false;
            this.activeSidebar = true;
            document.getElementById("dailyTracker").classList.remove("card-container");
            document.getElementById("dailyTracker").classList.add("card-containerActive");
            
        },
        showWeeklyTimeSheet(){
            this.activeDailyTracker = false;
            this.activeWeeklyTimeSheet = true;
            this.activeProtocolForm = false;
            this.activeSidebar = true;
            document.getElementById("weeklyTimeSheet").classList.remove("card-container")
            document.getElementById("weeklyTimeSheet").classList.add("card-containerActive")
        },
        showProtocolForm(){
            this.activeDailyTracker = false;
            this.activeWeeklyTimeSheet = false;
            this.activeProtocolForm = true;
            this.activeSidebar = true;
            document.getElementById("protocolForm").classList.remove("card-container")
            document.getElementById("protocolForm").classList.add("card-containerActive")
        }
    }
}
</script>

<template>
    <div>
        <div class="card">
        
            <div class="activeArea">
            <DailyTimeTracker v-if="activeDailyTracker" @click="setActiveDailyTracker" />
            <WeeklyTimeTrackerSheet v-if="activeWeeklyTimeSheet" @click="setActiveWeeklyTimeSheet" />
            <ProtocolForm v-if="activeProtocolForm" @click="setActiveProtocolForm" />
            </div>
            <div class="sidebar">
                <div class="sidebar-Row" v-if="activeSidebar">
                <div class="divButton" id="dailyTrackerButton" @click="showDailyTracker"> DailyTracker </div>
                <div class="divButton" @click="showWeeklyTimeSheet"> WeeklyTimeSheet </div>
                <div class="divButton" @click="showProtocolForm"> ProtocolFrom </div>
                </div>
            </div>
            
        
        </div>
        
    </div>
</template>

<style>
    html {
        font-size: 62,5%;
    }

    .card {
        margin: 7% auto 0 auto;
        display: flex;
        background: #dfe6e8;
        width: 70rem;
        height: 40rem;
        padding: 3rem;
        border-radius: 3rem;

    }
    .divButton{
        width: 80%;
        background-color: #86f075;
        height: 5rem;
        margin: 2rem auto;
        border-radius: 0.5rem;
        text-align: center;
        padding: 3rem 1.5rem;
        
    }
    .divButton:hover{
        cursor: pointer;
        background: darkgrey;
        color: #ffffff;
    }
    .activeArea{
        float: left;
        width: 100%;
        padding: 1rem;
        display: flex;
        
    }
    .sidebar{
       width: 10%;
        float: right;
    }
    
    button:hover {
        cursor: pointer;
    }
    .card-containerActive{
        width: 95%;
        
        height: 100%;
        border-radius: 1rem;
        margin-right: 1.5rem;
        padding: 0.5rem;
        background: #ffffff;
    }
    .card-containerActive .checkIn-Button{
        background-color: #86f075;
    }
    .card-containerActive .checkIn-Button:hover{
        background-color: #339413;
    }
    .card-containerActive .break-Button{
        background-color: #fcfa74;   
    }
    .card-containerActive .break-Button:hover{
        background-color: #fae20a;  
    }
    .card-containerActive .checkOut-Button{
        background-color: #fc7262;
    }
    .card-containerActive .checkOut-Button:hover{
        background-color: #d92916;
    }
    .card-containerActive .printButton{
        background-color: #a05aad;
    }
    .card-containerActive .printButton:hover{
        background-color: #7d1f8f;
        color: #ffffff;
    }
    .card-containerActive .sendButton{
        background-color: #2ea652;
    }
    .card-containerActive .sendButton:hover{
        background-color:#0b782c;
        color: #ffffff;
    }
    .button-RowCenterActive button{
         width: 15rem;
        padding: 1.5rem;
        margin: 0.5rem 2.3rem 0.5rem 2rem;
        border-radius: 1.5rem;
        border: none;
        text-align: center;
    }
</style>