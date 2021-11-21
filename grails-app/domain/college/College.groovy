package college

class College {

    // name of college
    String name
    String number // unique number assigned to college

    static hasMany = [
            departments: Department // college has many departments
    ]
    static constraints = {
        name blank: false
        number blank: false, unique: true
    }
}
