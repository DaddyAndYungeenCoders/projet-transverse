

python3 -m venv .venv
source .venv/bin/activate
pip3 install -r requirements.txt


export PATH=$PATH:/home/maxime.dubois.1/.local/bin

uvicorn API:app