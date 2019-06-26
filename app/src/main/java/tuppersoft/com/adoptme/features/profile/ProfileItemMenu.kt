package tuppersoft.com.adoptme.features.profile

data class ProfileItemMenu(val id: Int, val imageId: Int, val stringId: Int, val showArrow: Boolean = true) {
    companion object {
        const val MENU_PERSONAL_DATA = 1
        const val MENU_TERMS = 2
        const val MENU_LICENSE = 3
        const val MENU_CHANGELOG = 4
        const val MENU_LOGOUT = 5
    }
}