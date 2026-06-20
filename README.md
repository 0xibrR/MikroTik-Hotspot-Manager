# MikroTik Hotspot Manager

A Java Swing desktop application for generating MikroTik Hotspot users and exporting them in multiple formats.

## Features

* Generate MikroTik hotspot users automatically
* Multiple username generation modes
* Multiple password generation modes
* Custom username length
* Custom password length
* User preview before export
* QR Code generation
* PDF voucher generation
* CSV export
* RSC export
* Export history tracking
* Settings persistence
* Open generated PDF directly
* Open export folder directly
* Input validation

## Technologies Used

* Java
* Java Swing
* ZXing
* iTextPDF
* Gson

## Project Structure

```text
src/
├── model/
├── service/
├── ui/
└── Main.java
```

## Generated Files

The application generates:

```text
mikrotik_users.csv
mikrotik_users.rsc
2M_100Users.pdf
```

All exported files are stored inside:

```text
vouchers/
```

## Import Users To MikroTik

### Using RSC File

1. Generate users using the application.
2. Open WinBox and connect to your MikroTik router.
3. Go to **Files**.
4. Upload the generated `.rsc` file.
5. Open **New Terminal**.
6. Run:

```mikrotik
/import file-name=mikrotik_users.rsc
```

If the file is located inside the flash directory:

```mikrotik
/import file-name=flash/mikrotik_users.rsc
```

7. MikroTik will automatically create all hotspot users from the generated file.

## Screenshots

Add screenshots of:

* Main Window
* User Preview
* Generated PDF Vouchers

Example:

```text
screenshots/
├── main-window.png
├── preview-users.png
└── vouchers-pdf.png
```

## Future Improvements

* Direct MikroTik API integration
* User search and filtering
* Advanced voucher templates
* Additional export formats

## Author

Ibrahim Ramadan

## License

This project is available for educational and personal use.
