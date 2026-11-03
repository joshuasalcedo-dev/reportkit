<#-- @ftlvariable name="doc" type="io.joshuasalcedo.reportkit.invoice.InvoiceDocument" -->
<#-- @ftlvariable name="s" type="java.util.Map<String, Object>" -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${doc.title()} ${doc.number()}</title>
    <style>
        @page {
            size: A4 portrait;
            margin: 16mm 14mm 16mm 14mm;
            @bottom-right {
                content: "Page " counter(page) " of " counter(pages);
                font-size: ${s.tinyFontSize};
                color: ${s.mutedTextColor};
            }
        }
        body {
            font-family: ${s.fontFamily};
            font-size: ${s.bodyFontSize};
            color: ${s.textColor};
            line-height: ${s.lineHeight};
            margin: 0;
        }
        table { border-collapse: collapse; }
        .muted { color: ${s.mutedTextColor}; }
        .label {
            font-size: ${s.tinyFontSize};
            font-weight: 700;
            color: ${s.mutedTextColor};
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        .lines th {
            font-size: ${s.tinyFontSize};
            font-weight: 700;
            color: ${s.mutedTextColor};
            text-transform: uppercase;
            letter-spacing: 0.5px;
            padding: 6px 8px;
            border-bottom: 2px solid ${s.strongBorderColor};
        }
        .lines td {
            padding: 7px 8px;
            border-bottom: 1px solid ${s.lightBorderColor};
            vertical-align: top;
        }
        .num { text-align: right; white-space: nowrap; }
        tr { page-break-inside: avoid; }
    </style>
</head>
<body>

<#-- Seller on the left, the document's title and number on the right -->
<table style="width:100%; margin-bottom:22px;">
    <tr>
        <td style="vertical-align:top; width:55%;">
            <div style="font-size:${s.headingFontSize}; font-weight:700; color:${s.darkTextColor};">${doc.seller().name()}</div>
            <#list doc.seller().lines() as line>
            <div class="muted" style="font-size:${s.smallFontSize};">${line}</div>
            </#list>
        </td>
        <td style="vertical-align:top; text-align:right;">
            <div style="font-size:26px; font-weight:800; letter-spacing:1px; color:${s.primaryColor};">${doc.title()?upper_case}</div>
            <div style="font-size:${s.chartTitleFontSize}; font-weight:600; color:${s.darkTextColor};">${doc.number()}</div>
            <#if doc.stamp()?has_content>
            <div style="display:inline-block; margin-top:6px; padding:2px 10px; border:2px solid ${s.negativeColor}; color:${s.negativeColor}; font-weight:800; letter-spacing:2px; font-size:${s.smallFontSize};">${doc.stamp()}</div>
            </#if>
        </td>
    </tr>
</table>

<#-- Bill to on the left, the details on the right -->
<table style="width:100%; margin-bottom:22px;">
    <tr>
        <td style="vertical-align:top; width:55%; padding:10px 12px; background-color:${s.cardBackground}; border:1px solid ${s.borderColor};">
            <div class="label">Bill to</div>
            <div style="font-weight:700; color:${s.darkTextColor}; margin-top:2px;">${doc.billTo().name()}</div>
            <#list doc.billTo().lines() as line>
            <div class="muted" style="font-size:${s.smallFontSize};">${line}</div>
            </#list>
        </td>
        <td style="width:5%;">&#160;</td>
        <td style="vertical-align:top;">
            <table style="width:100%; font-size:${s.smallFontSize};">
                <#list doc.details() as d>
                <tr>
                    <td class="muted" style="padding:2px 0; font-weight:600;">${d.label()}</td>
                    <td class="num" style="padding:2px 0; color:${s.darkTextColor};<#if d.emphasized()> font-weight:700;</#if>">${d.value()}</td>
                </tr>
                </#list>
            </table>
        </td>
    </tr>
</table>

<#-- The lines -->
<table class="lines" style="width:100%; font-size:${s.tableFontSize}; -fs-table-paginate:paginate;">
    <thead>
        <tr>
            <th style="text-align:left;">Description</th>
            <th class="num" style="width:12%;">Qty</th>
            <th class="num" style="width:18%;">Unit price</th>
            <th class="num" style="width:18%;">Amount</th>
        </tr>
    </thead>
    <tbody>
        <#list doc.lines() as line>
        <tr>
            <td style="color:${s.darkTextColor};">${line.description()}</td>
            <td class="num">${line.quantity()}</td>
            <td class="num">${line.unitPrice()}</td>
            <td class="num" style="color:${s.darkTextColor};">${line.amount()}</td>
        </tr>
        </#list>
    </tbody>
</table>

<#-- What the lines add up to, under the amount column -->
<table style="width:100%; margin-top:10px; page-break-inside:avoid;">
    <tr>
        <td style="width:52%;">&#160;</td>
        <td>
            <table style="width:100%; font-size:${s.smallFontSize};">
                <#list doc.summary() as row>
                <#if row.emphasized()>
                <tr>
                    <td style="padding:8px 8px; font-weight:700; color:#ffffff; background-color:${s.primaryColor};">${row.label()}</td>
                    <td class="num" style="padding:8px 8px; font-weight:800; font-size:${s.chartTitleFontSize}; color:#ffffff; background-color:${s.primaryColor};">${row.value()}</td>
                </tr>
                <#else>
                <tr>
                    <td class="muted" style="padding:4px 8px; border-bottom:1px solid ${s.lightBorderColor};">${row.label()}</td>
                    <td class="num" style="padding:4px 8px; color:${s.darkTextColor}; border-bottom:1px solid ${s.lightBorderColor};">${row.value()}</td>
                </tr>
                </#if>
                </#list>
            </table>
        </td>
    </tr>
</table>

<#if doc.notes()?has_content>
<div style="margin-top:26px; padding-top:8px; border-top:1px solid ${s.borderColor}; page-break-inside:avoid;">
    <div class="label">Notes</div>
    <div style="font-size:${s.smallFontSize}; margin-top:2px;">${doc.notes()}</div>
</div>
</#if>

</body>
</html>
