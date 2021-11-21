package college

import grails.converters.JSON

class CollegeController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render College.list(params) as JSON
    }

    def show() {
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def college = College.findById(id)

        if (!college) {
            render 'Invalid Id'
            return
        }
        render college as JSON

    }

    def create(College college) {
        if (!college.save()) {
            render "Unable to create college beacuse of ${college.errors.allErrors.defaultMessage.join(',')}"
            return
        }
        render "College created with name: ${college.name}"
    }

    def update(College college) {
        def instance = College.findById(college.id)
        if (!college) {
            render 'Invalid Id'
            return
        }
        bindData(instance, college)
        if (!instance.save(flush: true)) {
            render "Unable to update college beacuse of ${instance.errors.allErrors.defaultMessage.join(',')}"
            return
        }
        render "College updated with name: ${instance.id},${instance.name}"
    }

    def delete() {
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def college = College.findById(id)

        if (!college) {
            render 'Invalid Id'
            return
        }
        college.delete(flush: true)
        render 'college deleted'
    }
}
