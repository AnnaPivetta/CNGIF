import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.ByteBuffer

class HdrImage(
    private val width: Int = 0,
    private val height: Int = 0,
    var pixels: Array<Color> = Array(width * height) { Color(0F, 0F, 0F) }
) {
    fun validCoordinates(x: Int, y: Int): Boolean {
        assert(0 <= x && x < width && 0 <= y && y < height)
        return true

    }

    fun pixelOffset(x: Int, y: Int): Int {
        assert(validCoordinates(x, y))
        return y * width + x
    }

    fun getPixel(x: Int, y: Int) : Color {
        return pixels[pixelOffset(x,y)]
    }

    fun writeFloatToStream (stream : OutputStream, value : Float ) {
        stream.write(ByteBuffer.allocate(4).putFloat(value).array())
    }

    fun writePfm (stream: OutputStream){
        val endianness = 1.0
        val header = "PF\n$width $height\n$endianness\n"
        stream.write(header.toByteArray())

        for (y in height-1..0) {
            for (x in 0..width-1) {
                var color = getPixel(x, y)
                writeFloatToStream(stream, color.r)
                writeFloatToStream(stream, color.g)
                writeFloatToStream(stream, color.b)
            }
        }
    }
}

