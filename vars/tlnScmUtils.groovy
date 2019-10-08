class ScmHelper {
    String name
    Integer age

    ScmHelper(name, age) {          
        this.name = name
        this.age = age
    }
}

def createScmHelper() {
    return [
        'attachment-service-lib',
        'bwfa',
        'case-lib',
        'cognitive-lib',
        'email-lib',
        'flowsets-lib',
        'knowledge',
        'notification-service-lib',
        'search',
        'shared-components-lib',
        'shared-components-samples',
        'shared-services-lib',
        'slm',
        'social-lib',
        'task-lib',
        'ticketing-lib'
    ]
}
