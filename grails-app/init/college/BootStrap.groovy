package college

class BootStrap {

    def init = { servletContext ->

        def departments = ['CS', 'BBA', 'SE', 'CHEM']
        def students = [
                [ name: 'talha', department: 'CS'],
                [ name: 'osama', department: 'CS'],
                [ name: 'hasan', department: 'BBA'],
                [ name: 'bilal', department: 'CS'],
                [ name: 'hanan', department: 'SE'],
                [ name: 'manan', department: 'SE'],
                [ name: 'usman', department: 'CHEM'],
                [ name: 'taha', department: 'CHEM'],
        ]
        def courses = [
                [ name: 'CS_COURSE_1', department: 'CS'],
                [ name: 'CS_COURSE_2', department: 'CS'],
                [ name: 'BBA_COURSE_1', department: 'BBA'],
                [ name: 'BBA_COURSE_2', department: 'CS'],
                [ name: 'SE_COURSE_1', department: 'SE'],
                [ name: 'SE_COURSE_2', department: 'SE'],
                [ name: 'CHEM_COURSE_1', department: 'CHEM'],
                [ name: 'CHEM_COURSE_2', department: 'CHEM'],
        ]

        departments.each { department ->
            println("creating department with name :${department}")
            def instance = new Department(name: department)
            if(!instance.save()){
                println("unable to create department: ${instance.errors}")
            }
        }

        courses.each { course ->
            println("creating course with data :${course}")
            def department = Department.findByName(course.department)
            if(department){
                def instance = new Course(name: course.name, department: department)
                if(!instance.save()){
                    println("unable to create department: ${instance.errors}")
                }
            }
        }

        students.each { student ->
            println("creating student with :${student}")
            def department = Department.findByName(student.department)
            if(department){
                def instance = new Student(name: student.name, department: department)
                if(!instance.save()){
                    println("unable to create student: ${instance.errors}")
                }
            }
        }
    }
    def destroy = {
    }
}
