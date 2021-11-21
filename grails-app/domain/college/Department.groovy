package college

class Department {

    String name

//    static belongsTo = [
//            college: College
//    ]
    static hasMany = [
            courses: Course,
            students: Student
    ]
    static constraints = {
        name blank: false, unique: true
    }
}
