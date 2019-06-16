package tuppersoft.com.domain.exception

sealed class Failure {

    object NetworkConnection : Failure()
    object ServerError : Failure()
    object UnknownError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}