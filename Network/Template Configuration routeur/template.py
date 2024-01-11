from jinja2 import Template

# Modèle Jinja2
template_text = """
Building configuration...

Current configuration : 1935 bytes
!
version 12.4
service timestamps debug datetime msec
service timestamps log datetime msec
no service password-encryption
!
hostname {{ hostname }}
!
boot-start-marker
boot-end-marker
!
!
no aaa new-model
memory-size iomem 5
no ip icmp rate-limit unreachable
ip tcp synwait-time 5
!
!
ip cef
no ip domain lookup
!
!         
!
!
!
template d
!
!
!
!
!
!
!
!
!
!
!
!
!
!
! 
!
!
!         
!
interface Loopback0
 ip address {{ loopback_ip }} 255.255.255.255
!
{% for interface in interfaces %}
interface {{ interface.name }}
 {% if interface.ip_address %}
 ip address {{ interface.ip_address }} {{ interface.subnet_mask }}
 {% else %}
 no ip address
 {% endif %}
 {% if interface.shutdown %}
 shutdown
 {% endif %}
 {% if interface.dhcp %}
 ip address dhcp
 {% endif %}
 duplex auto
 speed auto
!
{% endfor %}
router ospf 1
 router-id {{ ospf_router_id }}
 log-adjacency-changes
{% for network in ospf_networks %}
 network {{ network.address }} {{ network.wildcard_mask }} area {{ network.area }}
{% endfor %}
!
no ip http server
no ip http secure-server
!
!
!
no cdp log mismatch duplex
!
!
!
!
control-plane
!
!
!
!
!
!
!
!
!
line con 0
 exec-timeout 0 0
 privilege level 15
 logging synchronous
line aux 0
 exec-timeout 0 0
 privilege level 15
 logging synchronous
line vty 0 4
 login
!
!
end
"""

# Charger le modèle Jinja2
template = Template(template_text)

# Demander à l'utilisateur de saisir les valeurs des variables
hostname = input("Hostname: ")
loopback_ip = input("Loopback IP Address: ")

interfaces = []
interface_count = int(input("Number of interfaces: "))
for i in range(interface_count):
    interface_name = input(f"Interface {i+1} name (e.g., FastEthernet0/0): ")
    ip_address = input(f"IP Address for {interface_name} (or leave blank for 'no ip address'): ")
    subnet_mask = input(f"Subnet Mask for {interface_name} (if IP address provided): ")
    shutdown = input(f"Shutdown {interface_name}? (yes/no): ").lower() == 'yes'
    dhcp = input(f"Use DHCP for {interface_name}? (yes/no): ").lower() == 'yes'
    interfaces.append({'name': interface_name, 'ip_address': ip_address, 'subnet_mask': subnet_mask,
                       'shutdown': shutdown, 'dhcp': dhcp})

ospf_router_id = input("OSPF Router ID: ")

ospf_networks = []
ospf_network_count = int(input("Number of OSPF networks: "))
for i in range(ospf_network_count):
    address = input(f"OSPF Network {i+1} address: ")
    wildcard_mask = input(f"OSPF Network {i+1} wildcard mask: ")
    area = input(f"OSPF Network {i+1} area: ")
    ospf_networks.append({'address': address, 'wildcard_mask': wildcard_mask, 'area': area})

# Données pour le modèle Jinja2
data = {
    'hostname': hostname,
    'loopback_ip': loopback_ip,
    'interfaces': interfaces,
    'ospf_router_id': ospf_router_id,
    'ospf_networks': ospf_networks
}

# Appliquer les données au modèle Jinja2
rendered_config = template.render(data)

# Imprimer la configuration générée
print(rendered_config)

# Vous pouvez également enregistrer cette configuration dans un fichier
with open('router_config.txt', 'w') as file:
    file.write(rendered_config)

