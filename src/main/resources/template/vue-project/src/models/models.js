class Employee{
    constructor(employeeId, username, password, personnelNumber, firstName, lastName){
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.personnelNumber = personnelNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

class DailyWorkingHours{
    constructor(dailyWorkingHoursId, employee, date, totalDayWorkTime, totalBreakTime, checkIn, checkOut){
        this.dailyWorkingHoursId = dailyWorkingHoursId;
        this.employee = employee;
        this.date = date;
        this.totalDayWorkTime = totalDayWorkTime;
        this.totalBreakTime = totalBreakTime;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}

class DailyBreakTimes {
    constructor(dailyBreakTimesId, dailyWorkingHours, breakCheckIn, breakCheckOut){
        this.dailyBreakTimesId = dailyBreakTimesId;
        this.dailyWorkingHours = dailyWorkingHours;
        this.breakCheckIn = breakCheckIn;
        this.breakCheckOut = breakCheckOut;
    }
}

export { Employee, DailyWorkingHours, DailyBreakTimes };