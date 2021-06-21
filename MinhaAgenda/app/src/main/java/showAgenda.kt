class showAgenda (var name: String,
                  var phoneNumber : String,
                  var referenceOrEmail : String) {

    fun getNameAgenda() : String = name
    fun getPhoneNumberAgenda() : String = phoneNumber
    fun getReferenceOrEmailAgenda() : String = referenceOrEmail

    fun setNameAgenda(newName : String) {
        name = newName
    }

    fun setPhoneNumberAgenda(newPhoneNumber : String) {
        phoneNumber = newPhoneNumber
    }

    fun setReferenceOrEmailAgenda(newReferenceOrEmail : String) {
        referenceOrEmail = newReferenceOrEmail
    }
}