package com.core.common.dao.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

import com.core.common.dao.ICommonDao;
import com.core.common.dao.IGenericBaseCommonDao;
import com.core.common.model.common.*;

@Repository
public class CommonDao extends GenericBaseCommonDao implements ICommonDao, IGenericBaseCommonDao {

	@Override
	public <T> T UploadFile(UploadFile uploadFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletResponse viewOrDownloadFile(
			UploadFile uploadFile) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ����û��Ƿ����
	 * */
	/*public TSUser getUserByUserIdAndUserNameExits(TSUser user) {
		String password = PasswordUtil.encrypt(user.getUserName(), user.getPassword(), PasswordUtil.getStaticSalt());
		String query = "from TSUser u where u.userName = :username and u.password=:passowrd";
		Query queryObject = getSession().createQuery(query);
		queryObject.setParameter("username", user.getUserName());
		queryObject.setParameter("passowrd", password);
		List<TSUser> users = queryObject.list();
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}*/
	
	/**
	 * admin�˻���ʼ��
	 */
	/*public void pwdInit(TSUser user,String newPwd){
		String query ="from TSUser u where u.userName = :username ";
		Query queryObject = getSession().createQuery(query);
		queryObject.setParameter("username", user.getUserName());
		List<TSUser> users =  queryObject.list();
		if(null != users && users.size() > 0){
			user = users.get(0);
			String pwd = PasswordUtil.encrypt(user.getUserName(), newPwd, PasswordUtil.getStaticSalt());
			user.setPassword(pwd);
			save(user);
		}
		
	}*/
	

	/*public String getUserRole(TSUser user) {
		String userRole = "";
		List<TSRoleUser> sRoleUser = findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		for (TSRoleUser tsRoleUser : sRoleUser) {
			userRole += tsRoleUser.getTSRole().getRoleCode() + ",";
		}
		return userRole;
	}*/


	/**
	 * �ļ��ϴ�
	 * 
	 * @param request
	 * @throws Exception
	 */
	/*@SuppressWarnings("unchecked")
	public Object uploadFile(UploadFile uploadFile) {
		Object object = uploadFile.getObject();
		if(uploadFile.getFileKey()!=null)
		{
			updateEntitie(object);
		}
		else {
		try {
			uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
			MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
			ReflectHelper reflectHelper = new ReflectHelper(uploadFile.getObject());
			String uploadbasepath = uploadFile.getBasePath();// �ļ��ϴ���Ŀ¼
			if (uploadbasepath == null) {
				uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
			}
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			// �ļ����ݿⱣ��·��
			String path = uploadbasepath + "/";// �ļ�������Ӳ�̵����·��
			String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + "/" + path;// �ļ���Ӳ����ʵ·��
			File file = new File(realPath);
			if (!file.exists()) {
				file.mkdirs();// ������Ŀ¼
			}
			if (uploadFile.getCusPath() != null) {
				realPath += uploadFile.getCusPath() + "/";
				path += uploadFile.getCusPath() + "/";
				file = new File(realPath);
				if (!file.exists()) {
					file.mkdirs();// �����ļ��Զ�����Ŀ¼
				}
			}
			else {
				realPath += DataUtils.getDataString(DataUtils.yyyyMMdd) + "/";
				path += DataUtils.getDataString(DataUtils.yyyyMMdd) + "/";
				file = new File(realPath);
				if (!file.exists()) {
					file.mkdir();// �����ļ�ʱ����Ŀ¼
				}
			}
			String entityName = uploadFile.getObject().getClass().getSimpleName();
			// �����ļ��ϴ�·��
			if (entityName.equals("TSTemplate")) {
				realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + ResourceUtil.getConfigByName("templatepath") + "/";
				path = ResourceUtil.getConfigByName("templatepath") + "/";
			} else if (entityName.equals("TSIcon")) {
				realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + uploadFile.getCusPath() + "/";
				path = uploadFile.getCusPath() + "/";
			}
			String fileName = "";
			String swfName = "";
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();// ��ȡ�ϴ��ļ�����
				fileName = mf.getOriginalFilename();// ��ȡ�ļ���
				swfName = PinyinUtil.getPinYinHeadChar(oConvertUtils.replaceBlank(FileUtils.getFilePrefix(fileName)));// ȡ�ļ�������ĸ��ΪSWF�ļ���
				String extend = FileUtils.getExtend(fileName);// ��ȡ�ļ���չ��
				String myfilename="";
				String noextfilename="";//������չ��
				if(uploadFile.isRename())
				{
				   
				   noextfilename=DataUtils.getDataString(DataUtils.yyyymmddhhmmss)+StringUtil.random(8);//�Զ����ļ�����
				   myfilename=noextfilename+"."+extend;//�Զ����ļ�����
				}
				else {
				  myfilename=fileName;
				}
				
				String savePath = realPath + myfilename;// �ļ�����ȫ·��
				String fileprefixName = FileUtils.getFilePrefix(fileName);
				if (uploadFile.getTitleField() != null) {
					reflectHelper.setMethodValue(uploadFile.getTitleField(), fileprefixName);// ��̬����set�������ļ�������⸳ֵ
				}
				if (uploadFile.getExtend() != null) {
					// ��̬���� set�������ļ��������ݸ�ֵ
					reflectHelper.setMethodValue(uploadFile.getExtend(), extend);
				}
				if (uploadFile.getByteField() != null) {
					// �������ļ����������ݿ���
					reflectHelper.setMethodValue(uploadFile.getByteField(), StreamUtils.InputStreamTOByte(mf.getInputStream()));
				}
				File savefile = new File(savePath);
				if (uploadFile.getRealPath() != null) {
					// �����ļ����ݿ������·��
					reflectHelper.setMethodValue(uploadFile.getRealPath(), path + myfilename);
				}
				saveOrUpdate(object);
				// �ļ�������ָ��Ӳ��Ŀ¼
				FileCopyUtils.copy(mf.getBytes(), savefile);
//				if (uploadFile.getSwfpath() != null) {
//					// תSWF
//					reflectHelper.setMethodValue(uploadFile.getSwfpath(), path + swfName + ".swf");
//					SwfToolsUtil.convert2SWF(savePath);
//				}
//				FileCopyUtils.copy(mf.getBytes(), savefile);
				if (uploadFile.getSwfpath() != null) {
					// תSWF
					reflectHelper.setMethodValue(uploadFile.getSwfpath(), path + FileUtils.getFilePrefix(myfilename) + ".swf");
					SwfToolsUtil.convert2SWF(savePath);
				}

			}
		} catch (Exception e1) {
		}
		}
		return object;
	}*/

	/**
	 * �ļ����ػ�Ԥ��
	 * 
	 * @param request
	 * @throws Exception
	 * @throws Exception
	 */
	/*@SuppressWarnings("unchecked")
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile) {
		uploadFile.getResponse().setContentType("UTF-8");
		uploadFile.getResponse().setCharacterEncoding("UTF-8");
		InputStream bis = null;
		BufferedOutputStream bos = null;
		HttpServletResponse response = uploadFile.getResponse();
		HttpServletRequest request = uploadFile.getRequest();
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		String downLoadPath = "";
		long fileLength = 0;
		if (uploadFile.getRealPath() != null&&uploadFile.getContent() == null) {
			downLoadPath = ctxPath + uploadFile.getRealPath();
			fileLength = new File(downLoadPath).length();
			try {
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			if (uploadFile.getContent() != null)
				bis = new ByteArrayInputStream(uploadFile.getContent());
			fileLength = uploadFile.getContent().length;
		}
		try {
			if (!uploadFile.isView() && uploadFile.getExtend() != null) {
				if (uploadFile.getExtend().equals("text")) {
					response.setContentType("text/plain;");
				} else if (uploadFile.getExtend().equals("doc")) {
					response.setContentType("application/msword;");
				} else if (uploadFile.getExtend().equals("xls")) {
					response.setContentType("application/ms-excel;");
				} else if (uploadFile.getExtend().equals("pdf")) {
					response.setContentType("application/pdf;");
				} else if (uploadFile.getExtend().equals("jpg") || uploadFile.getExtend().equals("jpeg")) {
					response.setContentType("image/jpeg;");
				} else {
					response.setContentType("application/x-msdownload;");
				}
				response.setHeader("Content-disposition", "attachment; filename=" + new String((uploadFile.getTitleField() + "." + uploadFile.getExtend()).getBytes("GBK"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
			}
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
	}*/

	/*public Map<Object, Object> getDataSourceMap(Template template) {
		return DataSourceMap.getDataSourceMap();
	}*/

	/**
	 * ����XML importFile ����xml������
	 */
	/*public HttpServletResponse createXml(ImportFile importFile) {
		HttpServletResponse response = importFile.getResponse();
		HttpServletRequest request = importFile.getRequest();
		try {
			// ����document����
			Document document = DocumentHelper.createDocument();
			document.setXMLEncoding("UTF-8");
			// �������ڵ�
			String rootname = importFile.getEntityName() + "s";
			Element rElement = document.addElement(rootname);
			Class entityClass = importFile.getEntityClass();
			String[] fields = importFile.getField().split(",");
			// �õ���������ļ���
			List objList = loadAll(entityClass);
			Class classType = entityClass.getClass();
			for (Object t : objList) {
				Element childElement = rElement.addElement(importFile.getEntityName());
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i];
					// ��һΪʵ�������
					if (i == 0) {
						childElement.addAttribute(fieldName, String.valueOf(TagUtil.fieldNametoValues(fieldName, t)));
					} else {
						Element name = childElement.addElement(fieldName);
						name.setText(String.valueOf(TagUtil.fieldNametoValues(fieldName, t)));
					}
				}

			}
			String ctxPath = request.getSession().getServletContext().getRealPath("");
			File fileWriter = new File(ctxPath + "/" + importFile.getFileName());
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileWriter));

			xmlWriter.write(document);
			xmlWriter.close();
			// �������ɵ�XML�ļ�
			UploadFile uploadFile = new UploadFile(request, response);
			uploadFile.setRealPath(importFile.getFileName());
			uploadFile.setTitleField(importFile.getFileName());
			uploadFile.setExtend("bak");
			viewOrDownloadFile(uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}*/

	/**
	 * ����XML�ļ������ݵ������ݿ���
	 */
	/*@SuppressWarnings("unchecked")
	public void parserXml(String fileName) {
		try {
			File inputXml = new File(fileName);
			Class entityClass;
			// ��ȡ�ļ�
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();
			// �������ڵ��µ��ӽڵ�
			for (Iterator i = employees.elementIterator(); i.hasNext();) {
				Element employee = (Element) i.next();
				// ��ʵ��������õ�ʵ����
				entityClass = GenericsUtils.getEntityClass(employee.getName());
				// �õ�ʵ������
				Field[] fields = TagUtil.getFiled(entityClass);
				// �õ�ʵ���ID
				String id = employee.attributeValue(fields[0].getName());
				// �ж�ʵ���Ƿ��Ѵ���
				Object obj1 = getEntity(entityClass, id);
				// ʵ�岻����new��ʵ��
				if (obj1 == null) {
					obj1 = entityClass.newInstance();
				}
				// ���ݷ����ʵ�����Ը�ֵ
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					for (int k = 0; k < fields.length; k++) {
						if (node.getName().equals(fields[k].getName())) {
							String fieldName = fields[k].getName();
							String stringLetter = fieldName.substring(0, 1).toUpperCase();
							String setName = "set" + stringLetter + fieldName.substring(1);
							Method setMethod = entityClass.getMethod(setName, new Class[] { fields[k].getType() });
							String type = TagUtil.getColumnType(fieldName, fields);
							if (type.equals("int")) {
								setMethod.invoke(obj1, new Integer(node.getText()));
							} else if (type.equals("string")) {
								setMethod.invoke(obj1, node.getText().toString());
							} else if (type.equals("short")) {
								setMethod.invoke(obj1, new Short(node.getText()));
							} else if (type.equals("double")) {
								setMethod.invoke(obj1, new Double(node.getText()));
							} else if (type.equals("Timestamp")) {
								setMethod.invoke(obj1, new Timestamp(DataUtils.str2Date(node.getText(), DataUtils.datetimeFormat).getTime()));
							}
						}
					}
				}
				if (obj1 != null) {
					saveOrUpdate(obj1);
				} else {
					save(obj1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * ����ģ������JSON
	 * 
	 * @param all
	 *            ȫ������
	 * @param in
	 *            ��ӵ�еĶ���
	 * @param comboBox
	 *            ģ��
	 * @return
	 */
	/*public List<ComboTree> comTree(List<TSDepart> all, ComboTree comboTree) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (TSDepart depart : all) {
			trees.add(tree(depart, true));
		}
		return trees;

	}*/

	/*@SuppressWarnings("unchecked")
	public ComboTree tree(TSDepart depart, boolean recursive) {
		ComboTree tree = new ComboTree();
		tree.setId(oConvertUtils.getString(depart.getId()));
		tree.setText(depart.getDepartname());
		List<TSDepart> departsList = findByProperty(TSDepart.class, "TSPDepart.id", depart.getId());
		if (departsList != null && departsList.size() > 0) {
			tree.setState("closed");
			tree.setChecked(false);
			if (recursive) {// �ݹ��ѯ�ӽڵ�
				List<TSDepart> departList = new ArrayList<TSDepart>(departsList);
				//Collections.sort(departList, new SetListSort());// ����
				List<ComboTree> children = new ArrayList<ComboTree>();
				for (TSDepart d : departList) {
					ComboTree t = tree(d, true);
					children.add(t);
				}
				tree.setChildren(children);
			}
		}
		return tree;
	}*/

	/**
	 * ����ģ������ComboTree JSON
	 * 
	 * @param allȫ������
	 * @param in��ӵ�еĶ���
	 * @param comboTreeModelģ��
	 * @return
	 */
	/*public List<ComboTree> ComboTree(List all, ComboTreeModel comboTreeModel, List in) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (Object obj : all) {
			trees.add(comboTree(obj, comboTreeModel, in, false));
		}
		return trees;

	}*/

	// ����ComboTree
	/*private ComboTree comboTree(Object obj, ComboTreeModel comboTreeModel, List in, boolean recursive) {
		ComboTree tree = new ComboTree();
		Map<String, Object> attributes = new HashMap<String, Object>();
		ReflectHelper reflectHelper = new ReflectHelper(obj);
		String id = oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getIdField()));
		tree.setId(id);
		tree.setText(oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getTextField())));
		if (comboTreeModel.getSrcField() != null) {
			attributes.put("href", oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getSrcField())));
			tree.setAttributes(attributes);
		}
		if (in == null) {
		} else {
			if (in.size() > 0) {
				for (Object inobj : in) {
					ReflectHelper reflectHelper2 = new ReflectHelper(inobj);
					String inId = oConvertUtils.getString(reflectHelper2.getMethodValue(comboTreeModel.getIdField()));
					if (inId == id) {
						tree.setChecked(true);
					}
				}
			}
		}
		List tsFunctions = (List) reflectHelper.getMethodValue(comboTreeModel.getChildField());
		if (tsFunctions != null && tsFunctions.size() > 0) {
			tree.setState("closed");
			tree.setChecked(false);
			
			 * if (recursive) {// �ݹ��ѯ�ӽڵ� List<TSFunction> functionList = new ArrayList<TSFunction>(tsFunctions); Collections.sort(functionList, new SetListSort());// ���� List<ComboTree> children = new ArrayList<ComboTree>(); for (TSFunction f : functionList) { ComboTree t = comboTree(f,comboTreeModel,in, true); children.add(t); } tree.setChildren(children); }
			 
		}
		return tree;
	}*/
	
	/**
	 * �����������ݱ�
	 */
	/*public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel) {
		List<TreeGrid> treegrid = new ArrayList<TreeGrid>();
		for (Object obj : all) {
			ReflectHelper reflectHelper = new ReflectHelper(obj);
			TreeGrid tg = new TreeGrid();
			String id = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIdField()));
			String src = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getSrc()));
			String text = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getTextField()));
			if(StringUtils.isNotEmpty(treeGridModel.getOrder())){
				String order = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getOrder()));
				tg.setOrder(order);
			}
			tg.setId(id);
			if (treeGridModel.getIcon() != null) {
				String iconpath = TagUtil.fieldNametoValues(treeGridModel.getIcon(), obj).toString();
				if (iconpath != null) {
					tg.setCode(iconpath);
				} else {
					tg.setCode("");
				}
			}
			tg.setSrc(src);
			tg.setText(text);
			if (treeGridModel.getParentId() != null) {
				Object pid = TagUtil.fieldNametoValues(treeGridModel.getParentId(), obj);
				if (pid != null) {
					tg.setParentId(pid.toString());
				} else {
					tg.setParentId("");
				}
			}
			if (treeGridModel.getParentText() != null) {
				Object ptext = TagUtil.fieldNametoValues(treeGridModel.getTextField(), obj);
				if (ptext != null) {
					tg.setParentText(ptext.toString());
				} else {
					tg.setParentText("");
				}

			}
			List childList = (List) reflectHelper.getMethodValue(treeGridModel.getChildList());

			if (childList != null && childList.size() > 0) {
				tg.setState("closed");
			}
			if (treeGridModel.getRoleid() != null) {
				String[] opStrings = {};
				List<TSRoleFunction> roleFunctions = findByProperty(TSRoleFunction.class, "TSFunction.id", id);

				if (roleFunctions.size() > 0) {
					for (TSRoleFunction tRoleFunction : roleFunctions) {
						TSRoleFunction roleFunction = tRoleFunction;
						if (roleFunction.getTSRole().getId().toString().equals(treeGridModel.getRoleid())) {
							String bbString = roleFunction.getOperation();
							if (bbString != null) {
								opStrings = bbString.split(",");
								break;
							}
						}
					}
				}
				List<TSOperation> operateions = findByProperty(TSOperation.class, "TSFunction.id", id);
				StringBuffer attributes = new StringBuffer();
				if (operateions.size() > 0) {
					for (TSOperation tOperation : operateions) {
						if (opStrings.length < 1) {
							attributes.append("<input type=checkbox name=operatons value=" + tOperation.getId() + "_" + id + ">" + tOperation.getOperationname());
						} else {
							StringBuffer sb = new StringBuffer();
							sb.append("<input type=checkbox name=operatons");
							for (int i = 0; i < opStrings.length; i++) {
								if (opStrings[i].equals(tOperation.getId().toString())) {
									sb.append(" checked=checked");
								}
							}
							sb.append(" value=" + tOperation.getId() + "_" + id + ">" + tOperation.getOperationname());
							attributes.append(sb.toString());
						}
					}
				}
				tg.setOperations(attributes.toString());
			}

			treegrid.add(tg);
		}
		return treegrid;
	}*/

}
