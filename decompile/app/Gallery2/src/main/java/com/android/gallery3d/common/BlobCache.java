package com.android.gallery3d.common;

import com.android.gallery3d.util.GalleryLog;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.zip.Adler32;

public class BlobCache implements Closeable {
    private int mActiveBytes;
    private RandomAccessFile mActiveDataFile;
    private int mActiveEntries;
    private int mActiveHashStart;
    private int mActiveRegion;
    private Adler32 mAdler32;
    private byte[] mBlobHeader;
    private RandomAccessFile mDataFile0;
    private RandomAccessFile mDataFile1;
    private int mFileOffset;
    private RandomAccessFile mInactiveDataFile;
    private int mInactiveHashStart;
    private MappedByteBuffer mIndexBuffer;
    private FileChannel mIndexChannel;
    private RandomAccessFile mIndexFile;
    private byte[] mIndexHeader;
    private LookupRequest mLookupRequest;
    private int mMaxBytes;
    private int mMaxEntries;
    private int mSlotOffset;
    private int mVersion;

    public static class LookupRequest {
        public byte[] buffer;
        public long key;
        public int length;
    }

    private boolean getBlob(java.io.RandomAccessFile r16, int r17, com.android.gallery3d.common.BlobCache.LookupRequest r18) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:64:? in {6, 13, 19, 26, 35, 40, 41, 46, 53, 55, 60, 61, 63, 65, 66} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r15 = this;
        r6 = r15.mBlobHeader;
        r8 = r16.getFilePointer();
        r0 = r17;
        r12 = (long) r0;
        r0 = r16;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r0.seek(r12);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r0 = r16;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = r0.read(r6);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = 20;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r12 == r13) goto L_0x0028;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x0018:
        r12 = "BlobCache";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = "cannot read blob header";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        com.android.gallery3d.util.GalleryLog.w(r12, r13);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x0028:
        r12 = 0;
        r4 = readLong(r6, r12);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;
        r12 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));
        if (r12 != 0) goto L_0x003a;
    L_0x0033:
        r12 = 0;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x003a:
        r0 = r18;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = r0.key;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r12 == 0) goto L_0x0063;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x0042:
        r12 = "BlobCache";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13.<init>();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r14 = "blob key does not match: ";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r14);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r4);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.toString();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        com.android.gallery3d.util.GalleryLog.w(r12, r13);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x0063:
        r12 = 8;
        r10 = readInt(r6, r12);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 12;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r3 = readInt(r6, r12);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r0 = r17;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r3 == r0) goto L_0x0094;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x0073:
        r12 = "BlobCache";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13.<init>();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r14 = "blob offset does not match: ";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r14);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r3);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.toString();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        com.android.gallery3d.util.GalleryLog.w(r12, r13);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x0094:
        r12 = 16;
        r7 = readInt(r6, r12);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r7 < 0) goto L_0x00a4;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x009c:
        r12 = r15.mMaxBytes;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = r12 - r17;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = r12 + -20;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r7 <= r12) goto L_0x00c5;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x00a4:
        r12 = "BlobCache";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13.<init>();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r14 = "invalid blob length: ";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r14);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r7);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.toString();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        com.android.gallery3d.util.GalleryLog.w(r12, r13);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x00c5:
        r0 = r18;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = r0.buffer;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r12 == 0) goto L_0x00d2;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x00cb:
        r0 = r18;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = r0.buffer;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = r12.length;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r12 >= r7) goto L_0x00d8;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x00d2:
        r12 = new byte[r7];	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r0 = r18;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r0.buffer = r12;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x00d8:
        r0 = r18;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r2 = r0.buffer;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r0 = r18;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r0.length = r7;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r0 = r16;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = r0.read(r2, r12, r7);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r12 == r7) goto L_0x00f9;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x00e9:
        r12 = "BlobCache";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = "cannot read blob data";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        com.android.gallery3d.util.GalleryLog.w(r12, r13);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x00f9:
        r12 = 0;
        r12 = r15.checkSum(r2, r12, r7);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        if (r12 == r10) goto L_0x0121;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
    L_0x0100:
        r12 = "BlobCache";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13.<init>();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r14 = "blob checksum does not match: ";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r14);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r10);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.toString();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        com.android.gallery3d.util.GalleryLog.w(r12, r13);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x0121:
        r12 = 1;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x0128:
        r11 = move-exception;
        r12 = "BlobCache";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13.<init>();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r14 = "getBlob failed.";	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r14);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r14 = r11.getMessage();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.append(r14);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r13 = r13.toString();	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        com.android.gallery3d.util.GalleryLog.e(r12, r13);	 Catch:{ Throwable -> 0x0128, all -> 0x014e }
        r12 = 0;
        r0 = r16;
        r0.seek(r8);
        return r12;
    L_0x014e:
        r12 = move-exception;
        r0 = r16;
        r0.seek(r8);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.gallery3d.common.BlobCache.getBlob(java.io.RandomAccessFile, int, com.android.gallery3d.common.BlobCache$LookupRequest):boolean");
    }

    public BlobCache(String path, int maxEntries, int maxBytes, boolean reset) throws IOException {
        this(path, maxEntries, maxBytes, reset, 0);
    }

    public BlobCache(String path, int maxEntries, int maxBytes, boolean reset, int version) throws IOException {
        this.mIndexHeader = new byte[32];
        this.mBlobHeader = new byte[20];
        this.mAdler32 = new Adler32();
        this.mLookupRequest = new LookupRequest();
        this.mIndexFile = new RandomAccessFile(path + ".idx", "rw");
        this.mDataFile0 = new RandomAccessFile(path + ".0", "rw");
        this.mDataFile1 = new RandomAccessFile(path + ".1", "rw");
        this.mVersion = version;
        if (reset || !loadIndex()) {
            resetCache(maxEntries, maxBytes);
            if (!loadIndex()) {
                closeAll();
                throw new IOException("unable to load index");
            }
        }
    }

    public static void deleteFiles(String path) {
        deleteFileSilently(path + ".idx");
        deleteFileSilently(path + ".0");
        deleteFileSilently(path + ".1");
    }

    private static void deleteFileSilently(String path) {
        try {
            new File(path).delete();
        } catch (Throwable th) {
        }
    }

    public void close() {
        syncAll();
        closeAll();
    }

    private void closeAll() {
        closeSilently(this.mIndexChannel);
        closeSilently(this.mIndexFile);
        closeSilently(this.mDataFile0);
        closeSilently(this.mDataFile1);
    }

    private boolean loadIndex() {
        try {
            this.mIndexFile.seek(0);
            this.mDataFile0.seek(0);
            this.mDataFile1.seek(0);
            byte[] buf = this.mIndexHeader;
            if (this.mIndexFile.read(buf) != 32) {
                GalleryLog.w("BlobCache", "cannot read header");
                return false;
            } else if (readInt(buf, 0) != -1289277392) {
                GalleryLog.w("BlobCache", "cannot read header magic");
                return false;
            } else if (readInt(buf, 24) != this.mVersion) {
                GalleryLog.w("BlobCache", "version mismatch");
                return false;
            } else {
                this.mMaxEntries = readInt(buf, 4);
                this.mMaxBytes = readInt(buf, 8);
                this.mActiveRegion = readInt(buf, 12);
                this.mActiveEntries = readInt(buf, 16);
                this.mActiveBytes = readInt(buf, 20);
                if (checkSum(buf, 0, 28) != readInt(buf, 28)) {
                    GalleryLog.w("BlobCache", "header checksum does not match");
                    return false;
                } else if (this.mMaxEntries <= 0) {
                    GalleryLog.w("BlobCache", "invalid max entries");
                    return false;
                } else if (this.mMaxBytes <= 0) {
                    GalleryLog.w("BlobCache", "invalid max bytes");
                    return false;
                } else if (this.mActiveRegion != 0 && this.mActiveRegion != 1) {
                    GalleryLog.w("BlobCache", "invalid active region");
                    return false;
                } else if (this.mActiveEntries < 0 || this.mActiveEntries > this.mMaxEntries) {
                    GalleryLog.w("BlobCache", "invalid active entries");
                    return false;
                } else if (this.mActiveBytes < 4 || this.mActiveBytes > this.mMaxBytes) {
                    GalleryLog.w("BlobCache", "invalid active bytes");
                    return false;
                } else if (this.mIndexFile.length() != ((long) (((this.mMaxEntries * 12) * 2) + 32))) {
                    GalleryLog.w("BlobCache", "invalid index file length");
                    return false;
                } else {
                    byte[] magic = new byte[4];
                    if (this.mDataFile0.read(magic) != 4) {
                        GalleryLog.w("BlobCache", "cannot read data file magic");
                        return false;
                    } else if (readInt(magic, 0) != -1121680112) {
                        GalleryLog.w("BlobCache", "invalid data file magic");
                        return false;
                    } else if (this.mDataFile1.read(magic) != 4) {
                        GalleryLog.w("BlobCache", "cannot read data file magic");
                        return false;
                    } else if (readInt(magic, 0) != -1121680112) {
                        GalleryLog.w("BlobCache", "invalid data file magic");
                        return false;
                    } else {
                        this.mIndexChannel = this.mIndexFile.getChannel();
                        this.mIndexBuffer = this.mIndexChannel.map(MapMode.READ_WRITE, 0, this.mIndexFile.length());
                        this.mIndexBuffer.order(ByteOrder.LITTLE_ENDIAN);
                        setActiveVariables();
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            GalleryLog.e("BlobCache", "loadIndex failed." + ex.getMessage());
            return false;
        }
    }

    private void setActiveVariables() throws IOException {
        this.mActiveDataFile = this.mActiveRegion == 0 ? this.mDataFile0 : this.mDataFile1;
        this.mInactiveDataFile = this.mActiveRegion == 1 ? this.mDataFile0 : this.mDataFile1;
        this.mActiveDataFile.setLength((long) this.mActiveBytes);
        this.mActiveDataFile.seek((long) this.mActiveBytes);
        this.mActiveHashStart = 32;
        this.mInactiveHashStart = 32;
        if (this.mActiveRegion == 0) {
            this.mInactiveHashStart += this.mMaxEntries * 12;
        } else {
            this.mActiveHashStart += this.mMaxEntries * 12;
        }
    }

    private void resetCache(int maxEntries, int maxBytes) throws IOException {
        this.mIndexFile.setLength(0);
        this.mIndexFile.setLength((long) (((maxEntries * 12) * 2) + 32));
        this.mIndexFile.seek(0);
        byte[] buf = this.mIndexHeader;
        writeInt(buf, 0, -1289277392);
        writeInt(buf, 4, maxEntries);
        writeInt(buf, 8, maxBytes);
        writeInt(buf, 12, 0);
        writeInt(buf, 16, 0);
        writeInt(buf, 20, 4);
        writeInt(buf, 24, this.mVersion);
        writeInt(buf, 28, checkSum(buf, 0, 28));
        this.mIndexFile.write(buf);
        this.mDataFile0.setLength(0);
        this.mDataFile1.setLength(0);
        this.mDataFile0.seek(0);
        this.mDataFile1.seek(0);
        writeInt(buf, 0, -1121680112);
        this.mDataFile0.write(buf, 0, 4);
        this.mDataFile1.write(buf, 0, 4);
    }

    private void flipRegion() throws IOException {
        this.mActiveRegion = 1 - this.mActiveRegion;
        this.mActiveEntries = 0;
        this.mActiveBytes = 4;
        writeInt(this.mIndexHeader, 12, this.mActiveRegion);
        writeInt(this.mIndexHeader, 16, this.mActiveEntries);
        writeInt(this.mIndexHeader, 20, this.mActiveBytes);
        updateIndexHeader();
        setActiveVariables();
        clearHash(this.mActiveHashStart);
        syncIndex();
    }

    private void updateIndexHeader() {
        writeInt(this.mIndexHeader, 28, checkSum(this.mIndexHeader, 0, 28));
        this.mIndexBuffer.position(0);
        this.mIndexBuffer.put(this.mIndexHeader);
    }

    private void clearHash(int hashStart) {
        byte[] zero = new byte[1024];
        this.mIndexBuffer.position(hashStart);
        int count = this.mMaxEntries * 12;
        while (count > 0) {
            int todo = Math.min(count, 1024);
            this.mIndexBuffer.put(zero, 0, todo);
            count -= todo;
        }
    }

    public void insert(long key, byte[] data) throws IOException {
        if (data.length + 24 > this.mMaxBytes) {
            throw new RuntimeException("blob is too large!");
        }
        if ((this.mActiveBytes + 20) + data.length > this.mMaxBytes || this.mActiveEntries * 2 >= this.mMaxEntries) {
            flipRegion();
        }
        if (!lookupInternal(key, this.mActiveHashStart)) {
            this.mActiveEntries++;
            writeInt(this.mIndexHeader, 16, this.mActiveEntries);
        }
        insertInternal(key, data, data.length);
        updateIndexHeader();
    }

    public void removeEntry(long key) throws IOException {
        if (lookupInternal(key, this.mActiveHashStart)) {
            this.mIndexBuffer.putLong(this.mSlotOffset, 0);
        }
    }

    private void insertInternal(long key, byte[] data, int length) throws IOException {
        byte[] header = this.mBlobHeader;
        int sum = checkSum(data);
        writeLong(header, 0, key);
        writeInt(header, 8, sum);
        writeInt(header, 12, this.mActiveBytes);
        writeInt(header, 16, length);
        this.mActiveDataFile.write(header);
        this.mActiveDataFile.write(data, 0, length);
        this.mIndexBuffer.putLong(this.mSlotOffset, key);
        this.mIndexBuffer.putInt(this.mSlotOffset + 8, this.mActiveBytes);
        this.mActiveBytes += length + 20;
        writeInt(this.mIndexHeader, 20, this.mActiveBytes);
    }

    public byte[] lookup(long key) throws IOException {
        this.mLookupRequest.key = key;
        this.mLookupRequest.buffer = null;
        if (lookup(this.mLookupRequest)) {
            return this.mLookupRequest.buffer;
        }
        return null;
    }

    public boolean lookup(LookupRequest req) throws IOException {
        if (lookupInternal(req.key, this.mActiveHashStart) && getBlob(this.mActiveDataFile, this.mFileOffset, req)) {
            return true;
        }
        int insertOffset = this.mSlotOffset;
        if (!lookupInternal(req.key, this.mInactiveHashStart) || !getBlob(this.mInactiveDataFile, this.mFileOffset, req)) {
            return false;
        }
        if ((this.mActiveBytes + 20) + req.length > this.mMaxBytes || this.mActiveEntries * 2 >= this.mMaxEntries) {
            return true;
        }
        this.mSlotOffset = insertOffset;
        try {
            insertInternal(req.key, req.buffer, req.length);
            this.mActiveEntries++;
            writeInt(this.mIndexHeader, 16, this.mActiveEntries);
            updateIndexHeader();
        } catch (Throwable th) {
            GalleryLog.e("BlobCache", "cannot copy over");
        }
        return true;
    }

    private boolean lookupInternal(long key, int hashStart) {
        int slot = (int) (key % ((long) this.mMaxEntries));
        if (slot < 0) {
            slot += this.mMaxEntries;
        }
        int slotBegin = slot;
        while (true) {
            int offset = hashStart + (slot * 12);
            long candidateKey = this.mIndexBuffer.getLong(offset);
            int candidateOffset = this.mIndexBuffer.getInt(offset + 8);
            if (candidateOffset == 0) {
                this.mSlotOffset = offset;
                return false;
            } else if (candidateKey == key) {
                this.mSlotOffset = offset;
                this.mFileOffset = candidateOffset;
                return true;
            } else {
                slot++;
                if (slot >= this.mMaxEntries) {
                    slot = 0;
                }
                if (slot == slotBegin) {
                    GalleryLog.w("BlobCache", "corrupted index: clear the slot.");
                    this.mIndexBuffer.putInt(((slot * 12) + hashStart) + 8, 0);
                }
            }
        }
    }

    public void syncIndex() {
        try {
            this.mIndexBuffer.force();
        } catch (Throwable t) {
            GalleryLog.w("BlobCache", "sync index failed." + t.getMessage());
        }
    }

    public void syncAll() {
        syncIndex();
        try {
            this.mDataFile0.getFD().sync();
        } catch (Throwable t) {
            GalleryLog.w("BlobCache", "sync data file 0 failed." + t.getMessage());
        }
        try {
            this.mDataFile1.getFD().sync();
        } catch (Throwable t2) {
            GalleryLog.w("BlobCache", "sync data file 1 failed." + t2.getMessage());
        }
    }

    int getActiveCount() {
        int count = 0;
        for (int i = 0; i < this.mMaxEntries; i++) {
            int offset = this.mActiveHashStart + (i * 12);
            long candidateKey = this.mIndexBuffer.getLong(offset);
            if (this.mIndexBuffer.getInt(offset + 8) != 0) {
                count++;
            }
        }
        if (count == this.mActiveEntries) {
            return count;
        }
        GalleryLog.e("BlobCache", "wrong active count: " + this.mActiveEntries + " vs " + count);
        return -1;
    }

    int checkSum(byte[] data) {
        this.mAdler32.reset();
        this.mAdler32.update(data);
        return (int) this.mAdler32.getValue();
    }

    int checkSum(byte[] data, int offset, int nbytes) {
        this.mAdler32.reset();
        this.mAdler32.update(data, offset, nbytes);
        return (int) this.mAdler32.getValue();
    }

    static void closeSilently(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (Throwable th) {
            }
        }
    }

    static int readInt(byte[] buf, int offset) {
        return (((buf[offset] & 255) | ((buf[offset + 1] & 255) << 8)) | ((buf[offset + 2] & 255) << 16)) | ((buf[offset + 3] & 255) << 24);
    }

    static long readLong(byte[] buf, int offset) {
        long result = (long) (buf[offset + 7] & 255);
        for (int i = 6; i >= 0; i--) {
            result = (result << 8) | ((long) (buf[offset + i] & 255));
        }
        return result;
    }

    static void writeInt(byte[] buf, int offset, int value) {
        for (int i = 0; i < 4; i++) {
            buf[offset + i] = (byte) (value & 255);
            value >>= 8;
        }
    }

    static void writeLong(byte[] buf, int offset, long value) {
        for (int i = 0; i < 8; i++) {
            buf[offset + i] = (byte) ((int) (255 & value));
            value >>= 8;
        }
    }
}
