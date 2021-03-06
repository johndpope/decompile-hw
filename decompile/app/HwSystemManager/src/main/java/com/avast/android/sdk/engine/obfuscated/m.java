package com.avast.android.sdk.engine.obfuscated;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import javax.crypto.CipherOutputStream;

/* compiled from: Unknown */
public class m extends OutputStream {
    static final /* synthetic */ boolean a;
    private final OutputStream b;
    private final byte[] c;
    private final l d = new l(Math.max(Math.max(16, 20), 1024));
    private CipherOutputStream e = null;
    private n f = null;

    static {
        boolean z = false;
        if (!m.class.desiredAssertionStatus()) {
            z = true;
        }
        a = z;
    }

    public m(OutputStream outputStream, byte[] bArr) {
        this.b = outputStream;
        this.c = new byte[bArr.length];
        System.arraycopy(bArr, 0, this.c, 0, bArr.length);
    }

    public void a(boolean z) throws IOException {
        if (z) {
            try {
                if (this.e != null) {
                    Object obj = new byte[this.d.a()];
                    if (obj.length >= 20) {
                        this.d.a(obj);
                        this.e.write(obj, 0, obj.length - 20);
                        this.e.close();
                        this.e = null;
                        Object obj2 = new byte[20];
                        System.arraycopy(obj, obj.length - 20, obj2, 0, 20);
                        if (!Arrays.equals(this.f.a(), obj2)) {
                            throw new d("HMac verification fails");
                        }
                    }
                    throw new d("Too few bytes read for checksum");
                }
                throw new d("Too few bytes read to initialize");
            } catch (Throwable th) {
                if (this.e != null) {
                    this.e.close();
                }
                if (this.f != null) {
                    this.f.close();
                }
                if (this.b != null) {
                    this.b.close();
                }
            }
        }
        if (this.e != null) {
            this.e.close();
        }
        if (this.f != null) {
            this.f.close();
        }
        if (this.b != null) {
            this.b.close();
        }
    }

    public void close() throws IOException {
        a(true);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        try {
            a(false);
        } catch (IOException e) {
        }
    }

    public void flush() throws IOException {
        this.b.flush();
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) ((byte) i)});
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        int a;
        if (this.e == null && this.d.a() + i2 >= 16) {
            Object obj = new byte[16];
            a = this.d.a(obj);
            int i3 = 16 - a;
            if (i3 > 0) {
                System.arraycopy(bArr, i, obj, a, i3);
            }
            try {
                this.f = new n(e.a(this.c), this.b);
                try {
                    this.e = new CipherOutputStream(this.f, e.a(this.c, obj, 2));
                    if (i3 > 0) {
                        write(bArr, i + i3, i2 - i3);
                    }
                    return;
                } catch (Exception e) {
                    throw new d("Can't initialize Cipher: " + e.getMessage());
                }
            } catch (Exception e2) {
                throw new d("Can't initialize HMac: " + e2.getMessage());
            }
        }
        if (this.e != null) {
            int a2 = this.d.a();
            a = (i2 + a2) - this.d.c();
            if (a > 0) {
                byte[] bArr2 = new byte[a];
                int a3 = this.d.a(bArr2);
                if (a || a3 == a2) {
                    a2 = a - a3;
                    this.e.write(bArr2, 0, a3);
                    if (a2 > 0) {
                        this.e.write(bArr, i, a2);
                        i += a2;
                        i2 -= a2;
                    }
                } else {
                    throw new AssertionError("Readed too few bytes");
                }
            }
            this.d.a(bArr, i, i2);
        } else {
            this.d.a(bArr, i, i2);
        }
    }
}
