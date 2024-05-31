#!/bin/bash
echo "Лабораторные работы. Веб-программирование:" > "${GITHUB_WORKSPACE}/README.md"

for papka in `find ${GITHUB_WORKSPACE} -type d -not -path '*/.*' -a -not -path '/home/runner/work/web-lab/web-lab' -maxdepth 1`
do
smth=`basename ${papka}`
if [ "${smth}" == "web-lab" ]
then
continue
fi
echo "* [${smth}](${smth})" >> "${GITHUB_WORKSPACE}/README.md"
done