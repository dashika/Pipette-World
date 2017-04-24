# Pipette-World
---
The application allows you to determine in real time the color code of any object that has got into the camera lens. You can log in using your Adobe account and sync your palettes from within the application.
## DEMO
---
Image |Image
-------------|----------------- 
 ![](https://github.com/dashika/Pipette-World/blob/master/img/img1.png) |  ![](https://github.com/dashika/Pipette-World/blob/master/img/img2.png)

#### Used libraries
---
    compile 'com.android.support:support-v4:25.3.1'
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.android.support:design:25.3.1'

    /* ButterKnife */
    compile('com.jakewharton:butterknife:8.5.1') {
        exclude module: 'support-compat'
    }
    apt 'com.jakewharton:butterknife-compiler:8.5.1'

    /* Dagger 2 */
    compile 'com.google.dagger:dagger:2.0'
    apt 'com.google.dagger:dagger-compiler:2.0.2'

    /* RxJava */
    compile 'io.reactivex:rxandroid:1.0.1'
    compile 'io.reactivex:rxjava:1.0.14'
    compile 'com.hwangjr.rxbus:rxbus:1.0.5'
    compile 'com.amitshekhar.android:rx2-android-networking:1.0.0'

    /* Paperparcel */
    compile 'nz.bradcampbell:paperparcel:2.0.1'
    annotationProcessor 'nz.bradcampbell:paperparcel-compiler:2.0.1'

    /* Adobe auth */
    compile 'com.adobe.creativesdk.foundation:auth:0.9.1251'

    /* DATA BASE */
    compile 'com.michaelpardo:activeandroid:3.1.0-SNAPSHOT'

    /* CRASH REPORT FIREBASE */
    compile 'com.google.firebase:firebase-crash:10.0.1'

    compile 'com.android.support:recyclerview-v7:25.3.1'
    
## LICENSE
---
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
