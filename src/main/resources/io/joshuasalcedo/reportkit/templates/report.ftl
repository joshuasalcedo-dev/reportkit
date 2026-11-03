<#-- @ftlvariable name="report" type="io.joshuasalcedo.reportkit.report.Report" -->
<#-- @ftlvariable name="meta" type="java.util.Map<String, Object>" -->
<#-- @ftlvariable name="s" type="java.util.Map<String, Object>" -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${report.title()}</title>
    <style>
        @page {
            size: ${meta.pageSize} ${meta.orientation};
            margin: ${meta.marginTop}mm ${meta.marginRight}mm ${meta.marginBottom}mm ${meta.marginLeft}mm;
            @top-center  { content: element(page-header); }
            @bottom-left {
                content: element(footer-left);
                font-size: ${s.smallFontSize};
                color: ${s.mutedTextColor};
                vertical-align: middle;
            }
            @bottom-center {
                content: element(footer-center);
                font-size: ${s.smallFontSize};
                color: ${s.mutedTextColor};
                vertical-align: middle;
            }
            <#if meta.showPageNumbers>
            @bottom-right {
                content: "Page " counter(page) " of " counter(pages);
                font-size: ${s.smallFontSize};
                color: ${s.mutedTextColor};
                vertical-align: middle;
            }
            </#if>
        }
        body {
            font-family: ${s.fontFamily};
            font-size: ${s.bodyFontSize};
            color: ${s.textColor};
            line-height: ${s.lineHeight};
            margin: 0;
            padding: 0;
        }
        .page-header {
            position: running(page-header);
            width: 100%;
            border-bottom: ${s.headerBorderWidth}px solid ${s.borderColor};
            padding-bottom: 4px;
            margin-bottom: 8px;
        }
        .footer-left {
            position: running(footer-left);
        }
        .footer-center {
            position: running(footer-center);
        }
        .section-break {
            page-break-before: always;
        }
        table { border-collapse: collapse; }
    </style>
</head>
<body>

<#-- ── Running Header (uses icon, not full logo) ────────────── -->
<#if meta.hasHeader>
<div class="page-header">
    <table style="width:100%;">
        <tr>
            <td style="vertical-align:middle; text-align:left; padding:0; width:33%;">
                <#if meta.headerIconUrl?has_content>
                    <img src="${meta.headerIconUrl}" style="height:${s.headerIconSize}px; width:auto; vertical-align:middle; margin-right:4px;" alt="" />
                </#if>
                <#if meta.headerCompanyName?has_content>
                    <span style="font-size:8pt; font-weight:600; color:${s.strongBorderColor}; vertical-align:middle;">${meta.headerCompanyName}</span>
                </#if>
            </td>
            <td style="vertical-align:middle; text-align:center; padding:0; width:34%;">
                <span style="font-size:${s.smallFontSize}; color:${s.lightTextColor};">${report.title()}</span>
            </td>
            <td style="vertical-align:middle; text-align:right; padding:0; width:33%;">
                <#if meta.headerSubtitle?has_content>
                    <span style="font-size:${s.smallFontSize}; color:${s.mutedTextColor}; font-style:italic;">${meta.headerSubtitle}</span>
                </#if>
            </td>
        </tr>
    </table>
</div>
</#if>

<#-- ── Running Footer elements (left + center; page numbers via @page counter) ── -->
<#if meta.hasFooter>
<div class="footer-left">${meta.footerLeft!''}</div>
<div class="footer-center">${meta.footerCenter!''}</div>
</#if>

<#-- ── Cover / Title Block ────────────────────────────────────── -->
<div style="margin-bottom:${s.coverBottomMargin}px; padding-bottom:12px; border-bottom:${s.coverBorderWidth}px solid ${s.primaryColor};">

    <#-- Company logo + name -->
    <#if meta.coverLogoUrl?has_content || meta.headerCompanyName?has_content>
    <div style="margin-bottom:6px;">
        <#if meta.coverLogoUrl?has_content>
            <img src="${meta.coverLogoUrl}" style="height:${s.coverLogoHeight}px; width:auto; vertical-align:middle; margin-right:10px;" alt="Logo" />
        </#if>
        <#if meta.headerCompanyName?has_content>
            <span style="font-size:${s.coverCompanyFontSize}; font-weight:800; color:${s.darkTextColor}; vertical-align:middle; letter-spacing:-0.5px;">${meta.headerCompanyName}</span>
        </#if>
    </div>
    </#if>

    <#-- Tagline -->
    <#if meta.companyTagline?has_content>
        <div style="font-size:8pt; color:${s.mutedTextColor}; margin-bottom:6px; font-style:italic;">${meta.companyTagline}</div>
    </#if>

    <#-- Cover photo -->
    <#if meta.hasCoverPhoto && meta.coverPhotoUrl?has_content>
    <div style="margin-bottom:8px;">
        <img src="${meta.coverPhotoUrl}" style="width:100%; max-height:${s.coverPhotoMaxHeight}px; border-radius:${s.infoBoxRadius}px; object-fit:cover;" alt="${meta.coverPhotoCaption!''}" />
        <#if meta.coverPhotoCaption?has_content>
            <div style="font-size:${s.tinyFontSize}; color:${s.lightTextColor}; margin-top:2px; text-align:center; font-style:italic;">${meta.coverPhotoCaption}</div>
        </#if>
    </div>
    </#if>

    <#-- Report title -->
    <h2 style="font-size:${s.coverTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin:0 0 4px 0; letter-spacing:-0.3px;">
        ${report.title()}
    </h2>
    <#if report.description()?has_content>
        <p style="font-size:${s.bodyFontSize}; color:${s.mutedTextColor}; margin:2px 0 6px 0;">${report.description()}</p>
    </#if>

    <#-- Report meta (author only) -->
    <#if (report.author()!'')?has_content>
    <div style="font-size:${s.smallFontSize}; color:${s.lightTextColor};">
        <span style="font-weight:600; color:${s.mutedTextColor};">Author:</span> ${report.author()}
    </div>
    </#if>
</div>

<#-- ── Company Profile Section (financial report standard) ──── -->
<#if meta.hasCompanyProfile>
<div style="margin-bottom:16px; padding:10px 14px; background-color:${s.cardBackground}; border:1px solid ${s.borderColor}; border-radius:${s.infoBoxRadius}px;">
    <span style="font-size:${s.tinyFontSize}; font-weight:700; color:${s.strongBorderColor}; text-transform:uppercase; letter-spacing:0.5px;">Reporting Entity</span>
    <table style="width:100%; margin-top:4px; font-size:${s.smallFontSize};">
        <tr>
            <td style="padding:2px 10px 2px 0; font-weight:600; color:${s.mutedTextColor}; width:20%;">Entity Name</td>
            <td style="padding:2px 0; color:${s.textColor};">${meta.headerCompanyName}</td>
        </tr>
        <#if meta.companyRegistration?has_content>
        <tr>
            <td style="padding:2px 10px 2px 0; font-weight:600; color:${s.mutedTextColor};">Registration</td>
            <td style="padding:2px 0; color:${s.textColor};">${meta.companyRegistration}</td>
        </tr>
        </#if>
        <#if meta.companyAddress?has_content>
        <tr>
            <td style="padding:2px 10px 2px 0; font-weight:600; color:${s.mutedTextColor};">Registered Office</td>
            <td style="padding:2px 0; color:${s.textColor};">${meta.companyAddress}</td>
        </tr>
        </#if>
        <#if meta.companyWebsite?has_content>
        <tr>
            <td style="padding:2px 10px 2px 0; font-weight:600; color:${s.mutedTextColor};">Website</td>
            <td style="padding:2px 0; color:${s.textColor};">${meta.companyWebsite}</td>
        </tr>
        </#if>
        <#if meta.companyEmail?has_content || meta.companyPhone?has_content>
        <tr>
            <td style="padding:2px 10px 2px 0; font-weight:600; color:${s.mutedTextColor};">Contact</td>
            <td style="padding:2px 0; color:${s.textColor};">
                <#if meta.companyEmail?has_content>${meta.companyEmail}</#if>
                <#if meta.companyEmail?has_content && meta.companyPhone?has_content> &#x2022; </#if>
                <#if meta.companyPhone?has_content>${meta.companyPhone}</#if>
            </td>
        </tr>
        </#if>
    </table>
</div>
</#if>

<#-- ── Metadata ───────────────────────────────────────────────── -->
<#if report.metadata()?has_content>
<div style="margin-bottom:16px; padding:8px 14px; background-color:${s.cardBackground}; border:1px solid ${s.borderColor}; border-radius:${s.infoBoxRadius}px;">
    <span style="font-size:${s.tinyFontSize}; font-weight:700; color:${s.strongBorderColor}; text-transform:uppercase; letter-spacing:0.5px;">Metadata</span>
    <table style="width:100%; margin-top:4px; font-size:${s.smallFontSize};">
        <#list report.metadata() as key, value>
        <tr>
            <td style="padding:1px 10px 1px 0; font-weight:600; color:${s.mutedTextColor}; width:25%;">${key}</td>
            <td style="padding:1px 0; color:${s.textColor};">${value}</td>
        </tr>
        </#list>
    </table>
</div>
</#if>

<#-- ── Sections ───────────────────────────────────────────────── -->
<#list report.sections() as section>
<div<#if s.sectionPageBreak && (section?index > 0)> class="section-break"</#if> style="margin-bottom:${s.sectionGap}px;">

    <h2 style="page-break-after:avoid; font-size:${s.headingFontSize}; font-weight:700; color:${s.headingColor}; margin:0 0 6px 0; padding-bottom:4px; border-bottom:2px solid ${s.headingBorderColor};">
        ${section.heading()}
    </h2>

    <#if section.narrative()?has_content>
        <p style="font-size:${s.smallFontSize}; color:#4b5563; margin:4px 0 10px 0; line-height:1.5;">${section.narrative()}</p>
    </#if>

    <#-- Render grouped visualizations -->
    <#assign groups = meta.sectionGroups[section?index]>
    <#list groups as group>

        <#-- ── KPI Row (table grid, 3 columns) ── -->
        <#if group.type == "kpi_row">
            <table style="width:100%; margin-bottom:8px;">
                <tr>
                    <#list group.items as kpi>
                    <td style="width:${(100 / 3)?string["0"]}%; vertical-align:top; padding:<#if kpi?index == 0>0 4px 0 0<#elseif kpi?index == 2>0 0 0 4px<#else>0 4px</#if>;">
                        <div style="padding:${s.kpiPadding}; background-color:${s.cardBackground}; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px;">
                            <div style="font-size:${s.tinyFontSize}; font-weight:600; color:${s.mutedTextColor}; text-transform:uppercase; letter-spacing:0.5px; margin-bottom:4px;">
                                ${kpi.title()}
                            </div>
                            <div style="font-size:${s.kpiValueFontSize}; font-weight:800; color:${s.darkTextColor}; margin-bottom:2px; line-height:1.1;">
                                ${kpi.mainValue()}
                            </div>
                            <div style="font-size:${s.smallFontSize}; color:<#if kpi.isPositiveTrend()>${s.positiveColor}<#else>${s.negativeColor}</#if>;">
                                ${kpi.subValue()}
                            </div>
                        </div>
                    </td>
                    </#list>
                    <#-- Fill empty cells if less than 3 -->
                    <#if group.items?size == 1>
                    <td style="width:33%; padding:0 4px;">&#160;</td>
                    <td style="width:33%; padding:0 0 0 4px;">&#160;</td>
                    <#elseif group.items?size == 2>
                    <td style="width:33%; padding:0 0 0 4px;">&#160;</td>
                    </#if>
                </tr>
            </table>
        </#if>

        <#-- ── Single visualization ── -->
        <#if group.type == "single">
            <#assign viz = group.items[0]>
            <div style="margin-bottom:10px;<#if viz.type().name() != "DATA_TABLE"> page-break-inside:avoid;</#if>">

            <#-- ── Bar Chart ── -->
            <#if viz.type().name() == "BAR_CHART">
                <#assign maxVal = group.calc.max>
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <table style="width:100%;">
                        <#list viz.bars() as bar>
                        <tr>
                            <td style="padding:3px 8px 3px 0; font-size:${s.tableFontSize}; font-weight:500; color:${s.strongBorderColor}; width:22%; text-align:right;">
                                ${bar.label()}
                            </td>
                            <td style="padding:3px 0;">
                                <div style="background-color:${s.borderColor}; border-radius:${s.barRadius}px; height:${s.barHeight}px; width:100%;">
                                    <div style="background-color:${bar.color()!s.primaryColor}; border-radius:${s.barRadius}px; height:${s.barHeight}px; width:${((maxVal > 0)?then(bar.value() / maxVal * 100, 0))?string["0.#"]}%;">&#160;</div>
                                </div>
                            </td>
                            <td style="padding:3px 0 3px 8px; font-size:${s.tableFontSize}; color:${s.mutedTextColor}; width:14%; text-align:right;">
                                ${bar.value()?string["#,##0.00"]}
                            </td>
                        </tr>
                        </#list>
                    </table>
                    <div style="font-size:${s.tinyFontSize}; color:${s.lightTextColor}; margin-top:4px;">
                        ${viz.xAxisLabel()} &#x2022; ${viz.yAxisLabel()}
                    </div>
                </div>
            </#if>

            <#-- ── Pie / Donut Chart ── -->
            <#if viz.type().name() == "PIE_CHART" || viz.type().name() == "DONUT_CHART">
                <#assign segTotal = group.calc.total>
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <table style="width:100%; font-size:${s.smallFontSize};">
                        <#list viz.segments() as seg>
                        <#assign segColor = (seg.color()!'')?has_content?then(seg.color(), meta.defaultColors[seg?index % meta.defaultColors?size])>
                        <tr>
                            <td style="padding:3px 6px; width:16px;">
                                <div style="width:10px; height:10px; border-radius:50%; background-color:${segColor};">&#160;</div>
                            </td>
                            <td style="padding:3px 6px; font-weight:500; color:${s.strongBorderColor};">${seg.label()}</td>
                            <td style="padding:3px 6px; text-align:right; color:${s.textColor}; font-weight:600;">
                                ${seg.value()?string["#,##0.00"]}
                            </td>
                            <td style="padding:3px 6px; text-align:right; color:${s.mutedTextColor}; width:50px;">
                                <#if (segTotal > 0)>${(seg.value() / segTotal * 100)?string["0.#"]}%</#if>
                            </td>
                        </tr>
                        </#list>
                    </table>
                </div>
            </#if>

            <#-- ── Time Series Chart ── -->
            <#if viz.type().name() == "TIME_SERIES">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:2px;">${viz.title()}</div>
                    <div style="font-size:${s.tinyFontSize}; color:${s.lightTextColor}; margin-bottom:6px;">
                        Granularity: ${viz.granularity()} &#x2022; ${viz.data()?size} data points
                    </div>
                    <table style="width:100%; font-size:${s.tableFontSize};">
                        <thead>
                            <tr>
                                <th style="padding:4px 6px; text-align:left; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">Timestamp</th>
                                <th style="padding:4px 6px; text-align:right; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">${viz.yAxisLabel()}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list viz.data() as pt>
                            <tr>
                                <td style="padding:3px 6px; color:${s.strongBorderColor}; border-bottom:1px solid ${s.lightBorderColor};">
                                    ${group.calc.labels[pt?index]}
                                </td>
                                <td style="padding:3px 6px; text-align:right; color:${s.textColor}; border-bottom:1px solid ${s.lightBorderColor};">
                                    ${pt.value()?string["#,##0.00"]}
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </#if>

            <#-- ── Stacked Bar Chart ── -->
            <#if viz.type().name() == "STACKED_BAR_CHART">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <table style="width:100%; font-size:${s.tableFontSize};">
                        <thead>
                            <tr>
                                <th style="padding:4px 6px; text-align:left; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">Category</th>
                                <#list viz.series() as ser>
                                <th style="padding:4px 6px; text-align:right; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">${ser.name()}</th>
                                </#list>
                                <th style="padding:4px 6px; text-align:right; color:${s.strongBorderColor}; border-bottom:2px solid ${s.borderColor}; font-weight:700;">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list viz.categories() as cat>
                            <tr>
                                <td style="padding:3px 6px; font-weight:500; color:${s.strongBorderColor}; border-bottom:1px solid ${s.lightBorderColor};">${cat}</td>
                                <#list viz.series() as ser>
                                <td style="padding:3px 6px; text-align:right; color:${s.textColor}; border-bottom:1px solid ${s.lightBorderColor};">
                                    ${((cat?index < ser.values()?size)?then(ser.values()[cat?index], 0))?string["#,##0.00"]}
                                </td>
                                </#list>
                                <td style="padding:3px 6px; text-align:right; font-weight:700; color:${s.darkTextColor}; border-bottom:1px solid ${s.lightBorderColor};">
                                    ${group.calc.totals[cat?index]?string["#,##0.00"]}
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                    <div style="margin-top:6px; font-size:${s.tinyFontSize}; color:${s.mutedTextColor};">
                        <#list viz.series() as ser>
                            <#assign sColor = (ser.color()!'')?has_content?then(ser.color(), meta.defaultColors[ser?index % meta.defaultColors?size])>
                            <span style="margin-right:12px;">
                                <span style="display:inline-block; width:8px; height:8px; border-radius:2px; margin-right:3px; background-color:${sColor};">&#160;</span>
                                ${ser.name()}
                            </span>
                        </#list>
                    </div>
                </div>
            </#if>

            <#-- ── Line Chart ── -->
            <#if viz.type().name() == "LINE_CHART">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <#list viz.series() as ser>
                    <table style="width:100%; font-size:${s.tableFontSize}; margin-bottom:4px;">
                        <tr><td colspan="2" style="padding:3px 6px; font-weight:600; color:${s.strongBorderColor};">${ser.name()}</td></tr>
                        <#list ser.points() as pt>
                        <tr>
                            <td style="padding:2px 6px; color:${s.mutedTextColor};">${pt.label()}</td>
                            <td style="padding:2px 6px; text-align:right; color:${s.textColor};">${pt.value()?string["#,##0.00"]}</td>
                        </tr>
                        </#list>
                    </table>
                    </#list>
                </div>
            </#if>

            <#-- ── Area Chart ── -->
            <#if viz.type().name() == "AREA_CHART">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <#if viz.stacked()><div style="font-size:${s.tinyFontSize}; color:${s.lightTextColor}; margin-bottom:4px;">Stacked</div></#if>
                    <#list viz.series() as ser>
                    <table style="width:100%; font-size:${s.tableFontSize}; margin-bottom:4px;">
                        <tr><td colspan="2" style="padding:3px 6px; font-weight:600; color:${s.strongBorderColor};">${ser.name()}</td></tr>
                        <#list ser.points() as pt>
                        <tr>
                            <td style="padding:2px 6px; color:${s.mutedTextColor};">${pt.label()}</td>
                            <td style="padding:2px 6px; text-align:right; color:${s.textColor};">${pt.value()?string["#,##0.00"]}</td>
                        </tr>
                        </#list>
                    </table>
                    </#list>
                </div>
            </#if>

            <#-- ── Gauge Chart ── -->
            <#if viz.type().name() == "GAUGE_CHART">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:6px;">${viz.title()}</div>
                    <div style="font-size:${s.gaugeValueFontSize}; font-weight:800; color:${s.darkTextColor}; margin-bottom:4px; line-height:1.1;">
                        ${viz.value()?string["#,##0.00"]}
                        <#if (viz.unit()!'')?has_content>
                            <span style="font-size:${s.chartTitleFontSize}; color:${s.mutedTextColor}; font-weight:400;">${viz.unit()}</span>
                        </#if>
                    </div>
                    <#assign range = viz.maxValue() - viz.minValue()>
                    <#assign pct = (range > 0)?then([[0, (viz.value() - viz.minValue()) / range * 100]?max, 100]?min, 0)>
                    <div style="background-color:${s.borderColor}; border-radius:${s.gaugeRadius}px; height:${s.gaugeHeight}px; width:100%; margin-bottom:4px;">
                        <div style="height:${s.gaugeHeight}px; border-radius:${s.gaugeRadius}px; width:${pct?string["0.#"]}%; background-color:${s.primaryColor};">&#160;</div>
                    </div>
                    <div style="font-size:${s.tinyFontSize}; color:${s.lightTextColor};">
                        ${viz.minValue()?string["#,##0"]} &#x2014; ${viz.maxValue()?string["#,##0"]}
                    </div>
                    <#if viz.thresholds()?has_content>
                    <div style="margin-top:4px; font-size:${s.tinyFontSize};">
                        <#list viz.thresholds() as t>
                        <span style="margin-right:8px;">
                            <span style="display:inline-block; width:8px; height:8px; border-radius:2px; margin-right:2px; background-color:${t.color()};">&#160;</span>
                            <span style="color:${s.mutedTextColor};">${t.label()} &#x2264;${t.upTo()?string["#,##0"]}</span>
                        </span>
                        </#list>
                    </div>
                    </#if>
                </div>
            </#if>

            <#-- ── Data Table ── -->
            <#if viz.type().name() == "DATA_TABLE">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <table style="width:100%; font-size:${s.tableFontSize};">
                        <thead>
                            <tr>
                                <#list viz.headers() as h>
                                <th style="padding:4px 6px; text-align:<#if group.calc.numeric[h?index]>right<#else>left</#if>; font-weight:600; color:${s.strongBorderColor}; background-color:${s.cardBackground}; border-bottom:2px solid ${s.borderColor};">${h}</th>
                                </#list>
                            </tr>
                        </thead>
                        <tbody>
                            <#list viz.rows() as row>
                            <tr<#if s.tableStriped && row?index % 2 == 0> style="background-color:${s.tableStripeColor};"</#if>>
                                <#list row as cell>
                                <td style="padding:3px 6px; text-align:<#if (cell?index < group.calc.numeric?size) && group.calc.numeric[cell?index]>right<#else>left</#if>; color:${s.textColor}; border-bottom:1px solid ${s.lightBorderColor};">${cell}</td>
                                </#list>
                            </tr>
                            </#list>
                        </tbody>
                        <#if viz.totalRow()??>
                        <tfoot>
                            <tr>
                                <#list viz.totalRow() as cell>
                                <td style="padding:4px 6px; text-align:<#if (cell?index < group.calc.numeric?size) && group.calc.numeric[cell?index]>right<#else>left</#if>; font-weight:700; color:${s.darkTextColor}; border-top:2px solid ${s.strongBorderColor}; background-color:${s.lightBorderColor};">${cell}</td>
                                </#list>
                            </tr>
                        </tfoot>
                        </#if>
                    </table>
                </div>
            </#if>

            <#-- ── Heat Map ── -->
            <#if viz.type().name() == "HEAT_MAP">
                <#assign hmMax = group.calc.max>
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <div style="font-size:${s.tinyFontSize}; color:${s.lightTextColor}; margin-bottom:4px;">
                        X: ${viz.xLabel()} &#x2022; Y: ${viz.yLabel()}
                    </div>
                    <table style="width:100%; font-size:${s.tinyFontSize};">
                        <#if viz.xCategories()?has_content>
                        <thead>
                            <tr>
                                <th style="padding:3px;">&#160;</th>
                                <#list viz.xCategories() as xCat>
                                <th style="padding:3px 4px; text-align:center; color:${s.mutedTextColor}; font-weight:500;">${xCat}</th>
                                </#list>
                            </tr>
                        </thead>
                        </#if>
                        <tbody>
                            <#list viz.intensityMatrix() as row>
                            <tr>
                                <#if viz.yCategories()?has_content>
                                <td style="padding:3px 6px; font-weight:500; color:${s.strongBorderColor}; white-space:nowrap;"><#if (row?index < viz.yCategories()?size)>${viz.yCategories()[row?index]}</#if></td>
                                </#if>
                                <#list row as val>
                                <#assign opacity = (hmMax > 0)?then((val / hmMax * 0.8 + 0.05), 0.05)>
                                <#-- Blue (59,130,246) over white, mixed here: the PDF renderer draws translucent fills unreliably. -->
                                <#assign hmR = (255 - (255 - 59) * opacity)?round>
                                <#assign hmG = (255 - (255 - 130) * opacity)?round>
                                <#assign hmB = (255 - (255 - 246) * opacity)?round>
                                <td style="padding:3px 4px; text-align:center; color:${s.textColor}; background-color:rgb(${hmR?c},${hmG?c},${hmB?c});">
                                    ${val?string["0"]}
                                </td>
                                </#list>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </#if>

            <#-- ── Scatter Plot ── -->
            <#if viz.type().name() == "SCATTER_PLOT">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <#list viz.series() as ser>
                    <table style="width:100%; font-size:${s.tableFontSize}; margin-bottom:4px;">
                        <tr><td colspan="2" style="padding:3px 6px; font-weight:600; color:${s.strongBorderColor};">${ser.name()}</td></tr>
                        <tr>
                            <th style="padding:3px 6px; text-align:right; color:${s.mutedTextColor};">${viz.xAxisLabel()}</th>
                            <th style="padding:3px 6px; text-align:right; color:${s.mutedTextColor};">${viz.yAxisLabel()}</th>
                        </tr>
                        <#list ser.points() as pt>
                        <tr>
                            <td style="padding:2px 6px; text-align:right; color:${s.textColor};">${pt.x()?string["#,##0.00"]}</td>
                            <td style="padding:2px 6px; text-align:right; color:${s.textColor};">${pt.y()?string["#,##0.00"]}</td>
                        </tr>
                        </#list>
                    </table>
                    </#list>
                </div>
            </#if>

            <#-- ── Bubble Chart ── -->
            <#if viz.type().name() == "BUBBLE_CHART">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <table style="width:100%; font-size:${s.tableFontSize};">
                        <thead>
                            <tr>
                                <th style="padding:4px 6px; text-align:left; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">Label</th>
                                <th style="padding:4px 6px; text-align:right; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">${viz.xAxisLabel()}</th>
                                <th style="padding:4px 6px; text-align:right; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">${viz.yAxisLabel()}</th>
                                <th style="padding:4px 6px; text-align:right; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">${viz.sizeLabel()}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list viz.bubbles() as b>
                            <tr>
                                <td style="padding:3px 6px; color:${s.strongBorderColor};">${b.label()}</td>
                                <td style="padding:3px 6px; text-align:right; color:${s.textColor};">${b.x()?string["#,##0.00"]}</td>
                                <td style="padding:3px 6px; text-align:right; color:${s.textColor};">${b.y()?string["#,##0.00"]}</td>
                                <td style="padding:3px 6px; text-align:right; color:${s.textColor};">${b.size()?string["#,##0.00"]}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </#if>

            <#-- ── Radar Chart ── -->
            <#if viz.type().name() == "RADAR_CHART">
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <table style="width:100%; font-size:${s.tableFontSize};">
                        <thead>
                            <tr>
                                <th style="padding:4px 6px; text-align:left; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">Axis</th>
                                <#list viz.series() as ser>
                                <th style="padding:4px 6px; text-align:right; color:${s.mutedTextColor}; border-bottom:2px solid ${s.borderColor};">${ser.name()}</th>
                                </#list>
                            </tr>
                        </thead>
                        <tbody>
                            <#list viz.axes() as axis>
                            <tr>
                                <td style="padding:3px 6px; font-weight:500; color:${s.strongBorderColor};">${axis}</td>
                                <#list viz.series() as ser>
                                <td style="padding:3px 6px; text-align:right; color:${s.textColor};"><#if (axis?index < ser.values()?size)>${ser.values()[axis?index]?string["#,##0.00"]}</#if></td>
                                </#list>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </#if>

            <#-- ── Funnel Chart ── -->
            <#if viz.type().name() == "FUNNEL_CHART">
                <#assign fMax = group.calc.max>
                <div style="padding:${s.cardPadding}px; border:1px solid ${s.borderColor}; border-radius:${s.borderRadius}px; background-color:${s.pageBackground};">
                    <div style="font-size:${s.chartTitleFontSize}; font-weight:700; color:${s.darkTextColor}; margin-bottom:${s.chartTitleGap}px;">${viz.title()}</div>
                    <#list viz.stages() as stage>
                    <div style="margin-bottom:4px;">
                        <div style="font-size:${s.tableFontSize}; font-weight:500; color:${s.strongBorderColor}; margin-bottom:2px;">
                            ${stage.name()}
                            <span style="color:${s.mutedTextColor};"> &#x2014; ${stage.value()?string["#,##0"]}</span>
                        </div>
                        <#assign fPct = (fMax > 0)?then((stage.value() / fMax * 100), 100)>
                        <div style="background-color:${s.borderColor}; border-radius:${s.barRadius}px; height:${s.funnelBarHeight}px;">
                            <div style="height:${s.funnelBarHeight}px; border-radius:${s.barRadius}px; background-color:${stage.color()!s.primaryColor}; width:${fPct?string["0.#"]}%;">&#160;</div>
                        </div>
                    </div>
                    </#list>
                </div>
            </#if>

            </div>
        </#if>

    </#list>

</div>
</#list>

</body>
</html>
