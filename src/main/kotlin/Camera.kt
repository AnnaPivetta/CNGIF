/** A Camera observing the scene
 *
 * This class is an abstract class for implementing different kind of projection onto the screen for the
 * observer of the scene
 *
 * @param d The distance of the Camera from the screen (default is 1.0)
 * @param a The aspect ratio (width/height) of the screen (default is 1.6...MacBook standard)
 */

abstract class Camera(
    val d : Float = 1.0F,
    val a : Float = 1.6F

) {
    abstract fun fireRay (u : Float, v: Float) : Ray
}