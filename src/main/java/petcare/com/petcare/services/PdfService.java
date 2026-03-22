package petcare.com.petcare.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import petcare.com.petcare.dto.HistoriaClinicaDto;
import petcare.com.petcare.dto.MascotaDto;

import java.io.OutputStream;
import java.util.List;

public class PdfService {

    // Colores y fuentes
    private static final BaseColor COLOR_NEGRO     = new BaseColor(24, 24, 27);
    private static final BaseColor COLOR_GRIS      = new BaseColor(113, 113, 122);
    private static final BaseColor COLOR_GRIS_CLARO = new BaseColor(244, 244, 245);
    private static final BaseColor COLOR_BORDE     = new BaseColor(228, 228, 231);
    private static final BaseColor COLOR_AZUL      = new BaseColor(59, 130, 246);

    public void generarHistoriaClinica(MascotaDto mascota,
                                       List<HistoriaClinicaDto> historias,
                                       OutputStream out) throws DocumentException {

        Document doc = new Document(PageSize.A4, 50, 50, 60, 60);
        PdfWriter.getInstance(doc, out);
        doc.open();

        agregarEncabezado(doc, mascota);
        agregarDatosMascota(doc, mascota);
        agregarHistorial(doc, historias);
        agregarPiePagina(doc);

        doc.close();
    }

    // ── ENCABEZADO ───────────────────────────────────────────────
    private void agregarEncabezado(Document doc, MascotaDto mascota) throws DocumentException {

        // Barra superior con nombre de la clínica
        PdfPTable header = new PdfPTable(1);
        header.setWidthPercentage(100);

        PdfPCell celdaHeader = new PdfPCell();
        celdaHeader.setBackgroundColor(COLOR_NEGRO);
        celdaHeader.setBorder(Rectangle.NO_BORDER);
        celdaHeader.setPadding(20);

        Font fontClinica = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.WHITE);
        Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(161, 161, 170));

        Paragraph clinica = new Paragraph("🐾 PetCare", fontClinica);
        clinica.setAlignment(Element.ALIGN_LEFT);

        Paragraph subtitulo = new Paragraph("Sistema de Gestión Veterinaria", fontSubtitulo);
        subtitulo.setAlignment(Element.ALIGN_LEFT);
        subtitulo.setSpacingBefore(4);

        celdaHeader.addElement(clinica);
        celdaHeader.addElement(subtitulo);
        header.addCell(celdaHeader);

        doc.add(header);
        doc.add(Chunk.NEWLINE);

        // Título del documento
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, COLOR_NEGRO);
        Paragraph titulo = new Paragraph("Historia Clínica", fontTitulo);
        titulo.setSpacingBefore(10);
        titulo.setSpacingAfter(4);
        doc.add(titulo);

        Font fontNombreMascota = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, COLOR_GRIS);
        Paragraph nombreMascota = new Paragraph("Paciente: " + mascota.getNombre() + " — " + mascota.getEspecie(), fontNombreMascota);
        nombreMascota.setSpacingAfter(20);
        doc.add(nombreMascota);

        // Línea separadora
        agregarLinea(doc);
    }

    // ── DATOS DE MASCOTA Y DUEÑO ─────────────────────────────────
    private void agregarDatosMascota(Document doc, MascotaDto mascota) throws DocumentException {

        doc.add(Chunk.NEWLINE);

        // Tabla de dos columnas: mascota | dueño
        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(100);
        tabla.setSpacingAfter(20);

        // Columna 1 — Datos de la mascota
        PdfPCell celdaMascota = new PdfPCell();
        celdaMascota.setBorderColor(COLOR_BORDE);
        celdaMascota.setPadding(16);
        celdaMascota.setBackgroundColor(COLOR_GRIS_CLARO);

        Font fontSeccion = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, COLOR_GRIS);
        Font fontLabel = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, COLOR_GRIS);
        Font fontValor = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, COLOR_NEGRO);

        Paragraph tituloMascota = new Paragraph("DATOS DE LA MASCOTA", fontSeccion);
        tituloMascota.setSpacingAfter(10);
        celdaMascota.addElement(tituloMascota);

        celdaMascota.addElement(new Paragraph("Nombre", fontLabel));
        celdaMascota.addElement(new Paragraph(mascota.getNombre(), fontValor));
        celdaMascota.addElement(Chunk.NEWLINE);

        celdaMascota.addElement(new Paragraph("Especie", fontLabel));
        celdaMascota.addElement(new Paragraph(mascota.getEspecie(), fontValor));
        celdaMascota.addElement(Chunk.NEWLINE);

        celdaMascota.addElement(new Paragraph("Raza", fontLabel));
        celdaMascota.addElement(new Paragraph(
                mascota.getRaza() != null ? mascota.getRaza() : "No especificada", fontValor));
        celdaMascota.addElement(Chunk.NEWLINE);

        celdaMascota.addElement(new Paragraph("Edad", fontLabel));
        celdaMascota.addElement(new Paragraph(
                mascota.getEdad() != null ? mascota.getEdad() + " años" : "No especificada", fontValor));

        // Columna 2 — Datos del dueño
        PdfPCell celdaDueno = new PdfPCell();
        celdaDueno.setBorderColor(COLOR_BORDE);
        celdaDueno.setPadding(16);

        Paragraph tituloDueno = new Paragraph("DATOS DEL DUEÑO", fontSeccion);
        tituloDueno.setSpacingAfter(10);
        celdaDueno.addElement(tituloDueno);

        celdaDueno.addElement(new Paragraph("Nombre", fontLabel));
        celdaDueno.addElement(new Paragraph(mascota.getNombreDueno(), fontValor));
        celdaDueno.addElement(Chunk.NEWLINE);

        celdaDueno.addElement(new Paragraph("Cédula", fontLabel));
        celdaDueno.addElement(new Paragraph(mascota.getCedulaDueno(), fontValor));
        celdaDueno.addElement(Chunk.NEWLINE);

        celdaDueno.addElement(new Paragraph("Teléfono", fontLabel));
        celdaDueno.addElement(new Paragraph(
                mascota.getTelefonoDueno() != null ? mascota.getTelefonoDueno() : "—", fontValor));
        celdaDueno.addElement(Chunk.NEWLINE);

        celdaDueno.addElement(new Paragraph("Correo", fontLabel));
        celdaDueno.addElement(new Paragraph(
                mascota.getCorreoDueno() != null ? mascota.getCorreoDueno() : "—", fontValor));

        tabla.addCell(celdaMascota);
        tabla.addCell(celdaDueno);
        doc.add(tabla);

        agregarLinea(doc);
    }

    // ── HISTORIAL DE ENTRADAS ────────────────────────────────────
    private void agregarHistorial(Document doc, List<HistoriaClinicaDto> historias)
            throws DocumentException {

        doc.add(Chunk.NEWLINE);

        Font fontSeccion = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, COLOR_NEGRO);
        Paragraph tituloHistorial = new Paragraph("Historial Clínico", fontSeccion);
        tituloHistorial.setSpacingAfter(16);
        doc.add(tituloHistorial);

        if (historias.isEmpty()) {
            Font fontVacio = new Font(Font.FontFamily.HELVETICA, 11, Font.ITALIC, COLOR_GRIS);
            doc.add(new Paragraph("No hay entradas en el historial clínico.", fontVacio));
            return;
        }

        Font fontFecha   = new Font(Font.FontFamily.HELVETICA, 9,  Font.BOLD,   COLOR_AZUL);
        Font fontLabel   = new Font(Font.FontFamily.HELVETICA, 9,  Font.BOLD,   COLOR_GRIS);
        Font fontTexto   = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, COLOR_NEGRO);

        for (HistoriaClinicaDto historia : historias) {

            PdfPTable entrada = new PdfPTable(1);
            entrada.setWidthPercentage(100);
            entrada.setSpacingAfter(12);

            PdfPCell celda = new PdfPCell();
            celda.setBorderColor(COLOR_BORDE);
            celda.setPadding(16);

            // Fecha
            String fecha = historia.getFecha() != null ? historia.getFecha().toString() : "Sin fecha";
            Paragraph pFecha = new Paragraph(fecha, fontFecha);
            pFecha.setSpacingAfter(10);
            celda.addElement(pFecha);

            // Diagnóstico
            celda.addElement(new Paragraph("DIAGNÓSTICO", fontLabel));
            Paragraph pDiag = new Paragraph(historia.getDescripcion(), fontTexto);
            pDiag.setSpacingAfter(10);
            celda.addElement(pDiag);

            // Medicación
            if (historia.getMedicacion() != null && !historia.getMedicacion().isBlank()) {
                celda.addElement(new Paragraph("MEDICACIÓN", fontLabel));
                celda.addElement(new Paragraph(historia.getMedicacion(), fontTexto));
            }

            entrada.addCell(celda);
            doc.add(entrada);
        }
    }

    // ── PIE DE PÁGINA ────────────────────────────────────────────
    private void agregarPiePagina(Document doc) throws DocumentException {
        doc.add(Chunk.NEWLINE);
        agregarLinea(doc);

        Font fontPie = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, COLOR_GRIS);
        Paragraph pie = new Paragraph("Documento generado por PetCare — Sistema de Gestión Veterinaria", fontPie);
        pie.setAlignment(Element.ALIGN_CENTER);
        pie.setSpacingBefore(8);
        doc.add(pie);
    }

    // ── UTILIDAD ─────────────────────────────────────────────────
    private void agregarLinea(Document doc) throws DocumentException {
        PdfPTable linea = new PdfPTable(1);
        linea.setWidthPercentage(100);
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(COLOR_BORDE);
        celda.setBorder(Rectangle.NO_BORDER);
        celda.setFixedHeight(1f);
        linea.addCell(celda);
        doc.add(linea);
    }
}