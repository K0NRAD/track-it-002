<script>
import TestData from "../testdata.json"

console.log(TestData)

var dailyWorkingHoursData = TestData[1][1]

var employee = dailyWorkingHoursData.employee;

var employeeFirstName = employee.firstName;
var employeeLastName = employee.lastName;
var employeePersonnelNumber = employee.personnelNumber;

var fullName = employeeFirstName + " " + employeeLastName;

try{
    var today = new Date();
    const loadDate = () => {
        document.getElementById('dateElement3').innerHTML = today.getDate() + '/' + (today.getMonth()+1) + '/' + today.getFullYear();
    }
    window.setInterval(loadDate, 1000);

    const displayTime= ()=>{
        var dateTime = new Date();
        var hrs = dateTime.getHours();
        var min = dateTime.getMinutes();

        document.getElementById('hours3').innerHTML = hrs ;
        document.getElementById('minutes3').innerHTML = min;

    }
    setInterval(displayTime, 1000);
}
catch{
}

export default{
    data(){
        return{
            TestData: TestData,
            dailyWorkingHoursData: dailyWorkingHoursData,
            employeePersonnelNumber: employeePersonnelNumber,
            fullName: fullName
        }
        
    },
    methods: {
        
        PrintPdf(){
            window.print();
        }
    }
}


</script>
<template>
<div>
    <div class="card-container" id="weeklyTimeSheet" >
        <div class="dateTime-Container" >
            <div class="date-row">
                <span id="dateElement3" ></span>
            </div>
            <div class="time-row">
                <span id="hours3"></span>
                <span>:</span>
                <span id="minutes3"></span>
            </div>
        </div>
        <div class="container">
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
        </div>
        <div class="container">
            <div class="workTimeInformation" >
            <!-- maybe forEach again!? -->
                <div class="information-container" >
                    <span class="information-Header">Date</span>
                    <br>
                    <div v-for="date in TestData[1]" v-bind:key="date.date"> 
                        {{date.date}}
                    </div>
                </div>
                <div class="information-container">
                    <span class="information-Header">Check-In</span>
                    <br>
                    <div v-for="checkIn in TestData[1]" v-bind:key="checkIn.checkIn"> 
                        {{checkIn.checkIn}}

                    </div>
                </div>
                <div class="information-container">
                    <span class="information-Header">Check-Out:</span>
                    <br>
                    <div v-for="checkOut in TestData[1]" v-bind:key="checkOut.checkOut"> 
                        {{checkOut.checkOut}}

                    </div>
                </div>
                <div class="information-container">
                    <span class="information-Header">worked Time:</span>
                    <br>
                    <div v-for="workTime in TestData[1]" v-bind:key="workTime.totalDayWorkTime"> 
                        {{workTime.totalDayWorkTime}}

                    </div>
                </div>
                <div class="information-container">
                    <span class="information-Header">total break Time:</span>
                    <br>
                    <div v-for="breakTime in TestData[1]" v-bind:key="breakTime.totalBreakTime"> 
                        {{breakTime.totalBreakTime}}

                    </div>
                </div>
            </div>
        </div>
        <div class="button-Row">
            <button class="printButton" @click="PrintPdf">Print</button>
        </div>
    </div>
</div>
</template>
<style>
.card-container:hover .printButton {
    background: #a05aad;
}
.workTimeInformation{
    display: flex;
    width: 100%;
    
}
.information-container{
    width: 20%;
    padding: 0 2rem 0 2rem;
    margin-bottom: 2rem;
    font-size: 1.3rem;
}
.userInformation{
    font-size: 1.5rem;
    text-align: right;
    margin-bottom: 1rem;
    padding-right: 1rem ;
}
.dateTime-Container{
    font-size: 1.3rem;
    margin-bottom: 0.5rem;
}
.information-Header{
    height: 2rem;
    display: block;
    font-weight: 600;
}

    
</style>