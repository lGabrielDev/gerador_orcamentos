Dim objExcel, objWorkbook

' Defina o caminho do arquivo do Excel e o caminho de destino do PDF
filePathExcel = "C:\Users\leekbiel\Desktop\whats_excel_complete\src\main\resources\static\modelo_orcamento_and_script\modelo_simples.xlsm"
filePathSalvarPdf = "C:\Users\leekbiel\Desktop\whats_excel_complete\src\main\resources\static\modelo_orcamento_and_script\orcamento.pdf"

' Inicialize o objeto Excel
Set objExcel = CreateObject("Excel.Application")
objExcel.Visible = False ' Não exibe o Excel

' Abra o arquivo do Excel
Set objWorkbook = objExcel.Workbooks.Open(filePathExcel)

' Exporta a planilha ativa como PDF
objWorkbook.ActiveSheet.ExportAsFixedFormat 0, filePathSalvarPdf ,0, 1, 0,,,0

' Salve e feche o arquivo Excel
objWorkbook.Close True
objExcel.Quit

' Limpeza
Set objWorkbook = Nothing
Set objExcel = Nothing