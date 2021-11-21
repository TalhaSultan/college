package college

class Student {

    String name

    static belongsTo = [department: Department]
    static constraints = {
        name blank: false, unique: true
    }
}
