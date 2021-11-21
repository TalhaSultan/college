package college

class Course {

    String name

    static hasMany = [students: Student]
    static belongsTo = [department: Department]
    static constraints = {
        name blank: false, unique: true
    }
}
