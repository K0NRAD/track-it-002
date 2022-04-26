<script>
import TestData from "../testdata.json"
    var dailyWorkingHoursData = TestData[1][1]

    var employee = dailyWorkingHoursData.employee;

    var employeeFirstName = employee.firstName;
    var employeeLastName = employee.lastName;
    var employeePersonnelNumber = employee.personnelNumber;

    var fullName = employeeFirstName + " " + employeeLastName;

    

var hr = 0;
var min = 0;
var sec = 0;
var stoptime = true;

const timer = document.getElementById('stopwatch');

    const timerCycle= () =>{

        

            console.log(stoptime)
            if (stoptime == false) {
                sec = parseInt(sec);
                min = parseInt(min);
                hr = parseInt(hr);

            sec = sec + 1;

            if (sec == 60) {
            min = min + 1;
            sec = 0;
            }
            if (min == 60) {
            hr = hr + 1;
            min = 0;
            sec = 0;
            }

            if (sec < 10 || sec == 0) {
            sec = '0' + sec;
            }
            if (min < 10 || min == 0) {
            min = '0' + min;
            }
            if (hr < 10 || hr == 0) {
            hr = '0' + hr;
            }

            timer.innerHTML = hr + ':' + min + ':' + sec;

            setTimeout("timerCycle()", 1000);
            }
        }


    

    try{
        var today = new Date();
        const loadDate = () => {
            document.getElementById('dateElement').innerHTML = today.getDate() + '/' + (today.getMonth()+1) + '/' + today.getFullYear();
        }
        window.setInterval(loadDate, 1000);

        const displayTime= ()=>{
            var dateTime = new Date();
            var hrs = dateTime.getHours();
            var min = dateTime.getMinutes();
            var sec = dateTime.getSeconds();


            document.getElementById('hours').innerHTML = hrs ;
            document.getElementById('minutes').innerHTML = min;
        }
        setInterval(displayTime, 1000);
    }catch{
    }


var checkIn = "";
var checkOut = "";
var checkOutBreak = "";
var checkInBreak = "";
var breakOption = 0;
var breakTime = "";
var checkInBreakHours = 0
var checkOutBreakHours = 0
var checkInBreakMinutes = 0
var checkOutBreakMinutes = 0
var currentBreakTimeMinutes = 0
var currentBreakTimeHours = 0
var checkInHours = 0
var checkInMinutes = 0




export default{
    data(){
        return{
            breakOption: breakOption,
            currentCheckOut: checkOut,
            TestData: TestData,
            currentCheckIn : checkIn,
            checkOutBreak: checkOutBreak,
            checkInBreak: checkInBreak,
            breakTime: breakTime,
            checkInBreakHours: checkInBreakHours,
            checkOutBreakHours: checkOutBreakHours,
            checkInBreakMinutes: checkInBreakMinutes,
            checkOutBreakMinutes: checkOutBreakMinutes,
            showBreakTime: false,
            currentBreakTimeMinutes: currentBreakTimeMinutes,
            currentBreakTimeHours: currentBreakTimeHours,
            employeePersonnelNumber: employeePersonnelNumber,
            fullName: fullName,
            checkInHours: checkInHours,
            checkInMinutes: checkInMinutes,
            
            hr: hr,
            min: min,
            sec: sec,
            stoptime: stoptime

        
        }
    },
    methods:{
        
        CheckIn(){ 
            
            
            const currentDateTime = new Date();
            const currentHourCheckIn = currentDateTime.getHours()
            const currentMinuteCheckIn = currentDateTime.getMinutes()
            
            this.checkInHours = currentHourCheckIn
            this.checkInMinutes = currentMinuteCheckIn
            
            const currentTime = currentHourCheckIn + ":" + currentMinuteCheckIn

            checkIn = currentTime

            this.currentCheckIn = checkIn
   

        },
        CheckOut(){
            const currentDateTime = new Date();
            const currentHour = currentDateTime.getHours()
            const currentMinute = currentDateTime.getMinutes()
            const currentTime = currentHour + ":" + currentMinute
            checkOut = currentTime


            this.currentCheckOut = checkOut
            console.log(checkOut)

            
        },
        Break(){
            if(breakOption == 0){
                const currentDateTimeBreak = new Date();
                const currentHourBreakCheckIn = currentDateTimeBreak.getHours()
                const currentMinuteBreakCheckIn = currentDateTimeBreak.getMinutes()
                const currentTime = currentHourBreakCheckIn + ":" + currentMinuteBreakCheckIn
                
                checkInBreak = currentTime

                this.checkInBreakHours =  currentHourBreakCheckIn
                this.checkInBreakMinutes = currentMinuteBreakCheckIn
                
                console.log("BreakCheckIn")
                console.log(checkInBreak)

                breakOption++
            }
            else{
                const currentDateTimeBreak = new Date();
                const currentHourBreakCheckOut = currentDateTimeBreak.getHours()
                const currentMinuteBreakCheckOut = currentDateTimeBreak.getMinutes()
                const currentTime = currentHourBreakCheckOut + ":" + currentMinuteBreakCheckOut
                checkOutBreak = currentTime

                this.checkOutBreakHours = currentHourBreakCheckOut
                this.checkOutBreakMinutes = currentMinuteBreakCheckOut

                this.showBreakTime = true

                console.log("BreakCheckOUt")
                console.log(checkOutBreak)

                breakOption--

                
            }
               
        },
       startTimer() {
           console.log(stoptime)
           
            if (stoptime == true) {
                console.log("start")
                stoptime = false;
                timerCycle();
                
            }
        },
        stopTimer() {
            if (stoptime == false) {
            stoptime = true;
        }
        },
        
        resetTimer() {
            console.log("reset")

           timer.innerHTML = "00:00:00";
            stoptime = true;
            hr = 0;
            sec = 0;
            min = 0;
        },
        toggleTimer(){
            console.log("Hi2")
            console.log(stoptime)
            
            if (stoptime == false) {
                
                stoptime = true;
                console.log(stoptime)
                }
            
            else{
                if (stoptime == true) {
    
                    stoptime = false;
                    console.log(stoptime)
                    timerCycle();
                }
            }
        }

    },
    
    
}


</script>
<template>
<div class="card-container" id= "dailyTracker" >
    <div class="dateTime-Container">
        <div class="date-row">
            <span id="dateElement" ></span>
        </div>
        <div class="time-row">
           <span id="hours"></span>
                    <span>:</span>
                    <span id="minutes"></span>
        </div>
    </div>
    <div class="userInformation">
        <div class="profilPic">

        </div>
        <div class="userName">
            {{fullName}}
        </div>
        <div class="personnelNumber">
            	{{employeePersonnelNumber}}
        </div>
    </div>
    
    <div class="tracker">
        <div class="checkIn">
            <span>checkIn:</span>
            <div class="checkInOutput"> {{ currentCheckIn }} </div>
        </div>
        <div class="workTime">
            <span>worked Time:</span>
            <div class="stopwatch" id="stopwatch" >
                00:00:00
            </div>

        </div>
        <div class="breakTime">
            <span>break Time:</span>
            <span class="breakOutput" v-if="showBreakTime"> 
                {{this.currentBreakTimeHours = ((checkOutBreakHours - checkInBreakHours))}} 
                :
                {{this.currentBreakTimeMinutes = ((checkOutBreakMinutes - checkInBreakMinutes))}} 
            </span>
        </div>
    </div>
    <div class="button-RowCenter" id="button-RowCenter">
        <button class="checkIn-Button" @click="CheckIn(); startTimer()" id="start">Check-In</button>
        <button class="break-Button" @click="Break(); toggleTimer()" id="stop">Break</button>
        <button class="checkOut-Button" @click="CheckOut(); resetTimer()" id="reset">Check-Out</button>
    </div>

</div>

</template>
<style>
.card-container{
    width: 20rem;
    max-width: 20rem;
    height: 100%;
    border-radius: 1rem;
    margin-right: 1.5rem;
    padding: 0.5rem;
    background: linear-gradient(rgba(0, 0, 0, 0.25), rgba(0, 0, 0, 0.25));
    display: flexbox;
}
.card-container:hover{
    background: #ffffff;
    cursor: pointer;
    
}
.card-container button{
    background: grey;
}
.card-container:hover .checkIn-Button{
    background-color: #86f075;
}
.card-container:hover .break-Button{
    background-color: #fcfa74;  
}
.card-container:hover .checkOut-Button{
    background-color: #fc7262;
}
.dateTime-Container {
    margin-top: 2rem;
    display: flex
    ;  
}
.time-row{
    width: 40%;
    text-align: right;
}
.date-row{
    width: 50%;
    margin: 0;
    padding-left: 0.5rem;
    
}
.button-RowCenter button{
     width: 20rem;
        padding: 1.5rem;
        margin: 0.5rem 0 0.5rem 0;
        border-radius: 1.5rem;
        border: none;
}
.timer{
    float: right;
    width: 5rem;
    height: 2rem;
    background: #86f075;
}
.checkInOutput{
    float: right;
    
}
.tracker{
    font-size: 1.5rem;
    padding: 0.5rem 1rem 0.5rem 1rem;
    
    }
.stopwatch{
    float: right;
}
.breakOutput{
    float: right;
}

</style>