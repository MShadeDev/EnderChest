package client

import eu.mshade.mwork.binarytag.BinaryTagDriver
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.File

fun main() {
    val file = File("testPacket.dat")
    val readBytes = file.readBytes()
    var byteArrayInputStream = ByteArrayInputStream(readBytes)
    var dataInputStream = DataInputStream(byteArrayInputStream)
    val binaryTagDriver = BinaryTagDriver()

    var index = 0;
    while (readBytes.size-1 >= index){
        var size = dataInputStream.readInt()
        val start = index + 4
        var end = start + size
        if (end > readBytes.size) {
            end = readBytes.size
            size = end - start
        }
        println("size: $size")
        val segment = readBytes.copyOfRange(start, end)
        index += size + 4

        val readCompoundBinaryTag = binaryTagDriver.readCompoundBinaryTag(ByteArrayInputStream(segment))
        println(readCompoundBinaryTag.toPrettyString())

    }

    println("Packet World Initialisation")
    byteArrayInputStream = ByteArrayInputStream( byteArrayOf(10, 0, 0, 10, 0, 8, 109, 121, 45, 99, 104, 105, 108, 100, 8, 0, 4, 116, 101, 115, 116, 0, 5, 77, 105, 97, 111, 117, 0, 3, 0, 3, 117, 105, 100, 0, 0, 0, 1, 3, 0, 4, 117, 105, 100, 50, 0, 0, 0, 2, 3, 0, 4, 117, 105, 100, 51, 0, 0, 0, 2, 0))
    dataInputStream = DataInputStream(byteArrayInputStream)
//    println("size: ${dataInputStream.readInt()}")
    var readCompoundBinaryTag = binaryTagDriver.readCompoundBinaryTag(dataInputStream)
    println("available: ${dataInputStream.available()}")
    println(readCompoundBinaryTag.toPrettyString())

    println("Packet Player Initialisation")
    byteArrayInputStream = ByteArrayInputStream( byteArrayOf(0, 0, 4, -61, 21, 0, 0, 0, 0, 4, -68, 120, -38, -83, 84, -57, -110, -37, 70, 16, -123, -76, -110, 107, -75, -66, -40, 23, -105, -53, 7, -97, 124, 99, -83, -120, 64, 4, 30, 84, 37, -112, 4, 65, -128, 0, -120, -100, 110, 8, 3, 114, 64, -92, 69, 32, -62, 55, -7, 103, -4, 5, -2, 21, -125, -85, 80, -10, -35, 115, -103, -82, 126, 29, 94, -9, 76, -67, 39, -28, -121, 42, 11, 70, 80, 63, 33, 63, -98, -125, 28, -88, 117, -103, -64, 12, 60, 34, -17, -70, 14, -58, -56, 31, 65, 20, -81, -56, -112, -63, -97, 3, 106, -115, 63, -81, 80, -110, 121, 14, -103, 53, 120, 14, -104, -128, 102, -104, 112, 77, -83, -88, 123, 112, 49, -89, 34, 31, -10, 53, 0, 19, 88, -111, -28, 7, -28, -87, -86, -53, 10, -44, 45, 4, -51, 19, -126, 32, 111, -66, -59, 60, -74, 96, 104, -69, 26, 52, -113, -56, -5, 91, -112, 117, -32, -83, 11, -6, -14, 44, 108, 69, 52, 112, -80, 44, 34, -12, 75, -24, -78, 80, -40, -107, 103, -39, -12, 8, 121, -57, -31, -54, 46, 26, 79, 59, -113, 80, -58, -2, 40, 108, 89, 24, 29, -60, -101, -97, 103, -115, 111, 101, 87, 1, -78, -44, -100, 123, -15, 112, 13, 85, 28, 97, 37, -25, 28, 126, 50, 5, 84, -34, 89, 43, 47, 61, -109, -66, -61, -83, 60, 51, 90, -99, 120, -127, 84, 82, 13, -9, 13, -95, -39, 66, -10, 44, 20, -101, 49, -60, -3, 42, -28, -19, -109, 55, -9, -3, 90, -121, -113, 114, 59, 3, -71, -123, 42, -90, 5, 37, -10, -107, -41, 20, 56, 113, -25, -71, 58, 54, 99, -122, -17, -18, -79, -64, 21, 51, 127, 43, -100, 79, 112, -125, 70, -123, -99, 125, -115, 67, 125, -9, -126, -58, 51, 22, -115, -81, 24, -3, -38, 103, -26, 107, -95, -83, 104, -62, -1, -8, -18, -15, 115, -67, -2, 117, 78, 33, -97, -13, 14, 44, 37, -115, -21, 127, -43, 32, -37, -64, 33, 51, -113, 16, 47, 126, -95, 117, 97, 110, -93, 18, -95, 103, -32, 112, -25, 97, -35, 20, -98, -21, 21, 83, -55, 124, -57, -101, 100, -57, 90, -99, 28, -127, 80, 118, 22, -90, 56, -14, 48, -49, 57, -55, -90, -122, 122, -87, -121, -53, -68, -128, -33, -29, -26, 93, -12, -54, 36, 94, 20, 94, -55, -107, 29, 71, -8, -71, -110, -97, 76, -15, 114, -30, -71, -55, 79, -107, -36, -53, -25, -35, 108, -123, -29, 43, -73, 3, -38, 124, -29, -82, -95, 123, 77, 55, -2, 95, -18, 114, 42, -93, 50, 47, -9, -66, -55, 78, 62, -81, -115, -13, 44, -125, -20, 8, -125, 114, 127, -85, 84, -98, -68, 59, 79, -36, 34, 125, 83, 30, 61, 71, -124, 114, -54, 98, 51, 62, 42, -69, -13, -32, -17, 54, -48, 51, -29, -85, -17, -8, 87, 37, -107, 49, 15, -25, 8, -49, -68, 78, -14, -8, -99, -5, 124, 111, -42, -37, 2, -3, -12, -120, 124, 104, -32, -71, 8, -18, -33, -19, -19, -97, -57, 43, 108, -80, 120, -84, 25, 65, 95, -102, -73, -107, 93, 108, 46, -122, -24, 86, -128, -109, 98, -89, -40, -93, -39, 122, 97, -89, -103, -35, -38, -87, 40, -23, -57, 37, 19, 12, -128, -123, 11, -110, 107, 19, -69, 7, -114, -127, -19, 87, 47, -21, -64, 9, 117, -17, 106, -26, -80, 79, -43, 82, -121, 118, -21, 54, -44, -87, 17, -120, -105, 5, -107, -105, -5, -101, -58, -53, -72, 103, 37, -6, 49, -13, 101, -22, 44, 78, -112, 13, -31, -103, 94, 74, -127, -53, -67, 52, -105, -112, -18, 76, -39, -63, -80, -104, 8, -106, 107, 94, 20, -116, -75, -93, 43, -68, 58, 70, 87, 123, 95, 19, -118, 13, -105, -119, 41, 30, -41, -39, 73, 5, -122, 34, 86, 116, -87, -70, 24, -47, 92, -104, -67, -61, 122, 92, 3, -123, 112, -97, -71, -123, 68, -5, 80, -54, -92, -50, 63, 10, -34, 34, -76, 84, -69, -10, 44, -10, 18, -78, 7, 53, 32, -117, 28, 92, 110, 61, 97, 47, -90, -80, -115, -24, 18, -14, 64, -126, -110, 62, -122, -6, 45, 58, -58, 60, 56, -99, 108, -42, 50, 58, -72, 118, 14, 106, -69, 100, 56, 20, -26, -22, -115, 84, 68, 12, 107, 52, -43, 92, -42, 23, -102, 10, -127, -42, 31, 124, -46, -115, -124, 113, 39, -107, 121, 123, 90, -29, -83, -123, -69, -22, -107, -14, -45, 6, -75, -40, -15, -123, 114, -75, 36, -120, -51, 109, 98, 28, 23, 26, 80, -103, 77, 56, 12, -29, 72, -7, -104, 74, 75, 81, -124, -123, 9, -61, -39, 66, 114, -44, 6, -122, 61, 90, -53, -50, -63, -93, 64, 25, -121, -115, 127, 67, -27, 10, -115, -82, 33, 117, -82, -106, 73, -75, 97, 46, 19, -35, -74, -30, 53, 102, -75, -75, -91, -70, -89, -127, -61, 11, 106, -31, -11, 58, 92, -126, 101, -67, -16, 26, -1, -56, -19, 51, -102, -100, -41, 44, 42, -60, 66, 25, 25, 87, -46, 26, -101, 28, -80, -46, 38, -85, -87, 32, -45, -11, 33, 41, 41, -89, 59, 59, -128, -90, -54, -12, 50, -88, -71, 107, -15, -60, -86, 31, 45, 116, 43, 114, 94, 107, 88, -67, -95, 110, -53, -115, -27, 77, 76, -19, 7, -92, -11, 98, 56, -37, 45, 99, -17, -29, 125, -29, -33, -108, -61, 65, 117, -31, -95, -9, 64, -83, 1, -70, 46, 91, 117, 35, 38, 122, -67, 57, 17, -48, -73, -35, -52, -110, -100, 29, -19, -23, -71, 1, -6, 116, -21, -23, -107, 9, -44, -93, 69, 50, -62, -106, 84, -20, -123, -83, 57, 120, -114, -73, 26, 103, -89, -73, -68, -39, 76, 59, 14, -84, -70, 41, -14, -86, -119, -9, -93, -28, -106, 26, 24, 78, -113, 106, 125, 0, -44, 75, 35, 6, 121, 114, -91, 110, 78, -115, -45, 112, -32, 87, 97, -111, 73, -83, 30, 18, -108, -85, -58, -122, -69, -115, -92, 51, 48, -69, -28, -122, 70, -22, 102, -61, 5, 97, -90, -29, -23, 2, -59, 73, -26, 19, -126, 60, 32, -65, -26, -80, 0, 81, 29, 36, -19, 44, -55, 109, 25, -107, -103, 13, -22, 6, -106, -59, -84, -90, -53, 39, -28, -25, 25, 109, -115, 50, -70, -126, -106, -115, -29, 89, 79, 103, 65, 125, 11, 43, -28, -73, 107, 23, -126, 122, -58, 64, -13, 49, -66, -61, -11, 71, 88, -76, -77, 39, -56, 30, -112, 119, 85, 89, -73, 8, -14, -9, 95, -56, 19, -14, 75, 14, -38, 32, 14, -38, -32, 8, 70, -5, 46, -60, -101, -18, 94, 108, 70, 30, -77, 50, 10, -38, -71, -45, 44, -47, 125, 89, 103, -15, -41, -21, 7, -28, -51, -16, -7, 119, -28, -11, -52, -10, -8, -7, -89, -17, -10, -12, -51, -1, 30, 121, 24, -125, -2, -117, -11, -66, -126, 109, 116, -7, -30, 127, 64, 30, -85, -32, 94, 95, -120, 95, -79, 127, 0, -13, -24, 10, -40))
    dataInputStream = DataInputStream(byteArrayInputStream)
    println("size: ${dataInputStream.readInt()}")
     readCompoundBinaryTag = binaryTagDriver.readCompoundBinaryTag(dataInputStream)
    println("available: ${dataInputStream.available()}")
    println(readCompoundBinaryTag.toPrettyString())

}

